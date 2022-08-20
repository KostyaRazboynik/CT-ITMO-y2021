#include "return_codes.h"

#include <malloc.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>

float EPS = 1E-5f; //include guards + free(NULL) + mode fopen

void free2DArray(long long n, float **expandedMatrix)	 // очищение массива из памяти
{
	for (long long i = 0; i < n; i++)
	{
		free(expandedMatrix[i]);
	}
	free(expandedMatrix);
}

int fileException(FILE *file)	 // проверка наличия файла
{
	if (file == NULL)
	{
		printf("\nfile not found\n");
		return 0;
	}
	return 1;
}

void printMatrix(long long n, long long m, float **expandedMatrix)	  // вывод матрицы в консоль
{
	for (long long i = 0; i < n; i++)
	{
		for (long long j = 0; j < m; j++)
		{
			printf("%g ", expandedMatrix[i][j]);
		}
		printf("\n");
	}
}

int gauss(long long n, long long flag, float **expandedMatrix, FILE *out)	 // обработка результатов предыдущих
																			 // вычислений + вычисление ответа
{
	if (flag == 0)
	{
		fprintf(out, "no solution\n");
		return ERROR_SUCCESS;
	}
	else if (flag == 2)
	{
		fprintf(out, "many solutions\n");
		return ERROR_SUCCESS;
	}

	float sum;
	float *x = (float *)malloc(n * sizeof(float));
	if (x == NULL)
	{
		printf("not enough memory to solutions array");
		return ERROR_NOT_ENOUGH_MEMORY;
	}

	x[n - 1] = expandedMatrix[n - 1][n] / expandedMatrix[n - 1][n - 1];

	for (long long i = n - 2; i >= 0; i--)
	{
		sum = 0;
		for (long long j = i + 1; j < n; j++)
		{
			sum += expandedMatrix[i][j] * x[j];
		}
		if (fabsf(expandedMatrix[i][i]) < EPS)
		{
			if (fabsf(expandedMatrix[i][n] - sum) > EPS)
			{
				fprintf(out, "no solution \n");
				free(x);
				return ERROR_SUCCESS;
			}
			fprintf(out, "many solutions\n");
			free(x);
			return ERROR_SUCCESS;
		}
		else
		{
			float temp = (expandedMatrix[i][n] - sum) / expandedMatrix[i][i];
			if (fabsf(temp) < EPS)
			{
				x[i] = fabsf(temp);
				continue;
			}
			x[i] = temp;
		}
	}

	for (long long i = 0; i < n; i++)
	{
		fprintf(out, "%g\n", x[i]);
	}

	free(x);

	return ERROR_SUCCESS;
}

long long rankOfMatrix(long long n, long long m, float **expandedMatrix)	// нахождение ранга в матрице, приведенной к
																			// ступенчатому виду
{
	long long rank = 0;

	for (long long i = 0; i < n; i++)
	{
		for (long long j = 0; j < m; j++)
		{
			if (fabsf(expandedMatrix[i][j]) > EPS)
			{
				rank++;
				break;
			}
		}
	}
	return rank;
}

void swapMatrix(long long n, long long m, float **expandedMatrix)	 // "сортировка" строк матрицы по нулевому префиксу
{
	for (long long l = 0; l < n; l++)
	{
		for (long long i = 0; i < n - 1; i++)
		{
			long long zeroPrefCurr = 0;
			long long zeroPrefNext = 0;
			for (long long j = 0; j < m; j++)
			{
				if (fabsf(expandedMatrix[i][j]) < EPS)
				{
					zeroPrefCurr++;
					continue;
				}
				break;
			}
			for (long long j = 0; j < m; j++)
			{
				if (fabsf(expandedMatrix[i + 1][j]) < EPS)
				{
					zeroPrefNext++;
					continue;
				}
				break;
			}

			if (zeroPrefCurr > zeroPrefNext)
			{
				for (long long k = 0; k < m; k++)
				{
					float temp = expandedMatrix[i][k];
					expandedMatrix[i][k] = expandedMatrix[i + 1][k];
					expandedMatrix[i + 1][k] = temp;
				}
			}
		}
	}
}

long long rankOfExpandedMatrix(long long n, long long m, float **expandedMatrix)	// ранг матрицы + приведение к
																					// ступенчатому виду
{
	float c;

	swapMatrix(n, m, expandedMatrix);	 //сортировка строк

	for (long long j = 0; j < n; j++)
	{
		for (long long i = 0; i < n; i++)
		{
			if (i > j)
			{
				if (expandedMatrix[j][j] == 0)
				{
					continue;
				}

				c = expandedMatrix[i][j] / expandedMatrix[j][j];
				for (long long k = 0; k < m; k++)
				{
					expandedMatrix[i][k] = expandedMatrix[i][k] - c * expandedMatrix[j][k];
				}
			}
		}
	}

	swapMatrix(n, m, expandedMatrix);
	return rankOfMatrix(n, m, expandedMatrix);
}

int main(int argc, char **argv)
{
	if (argc != 3)
	{
		printf("incorrect number of command line arguments");
		return ERROR_INVALID_DATA;
	}

	FILE *in = fopen(argv[1], "r");
	if (!fileException(in))
	{
		return ERROR_FILE_NOT_FOUND;
	}

	long long N = -1;
	fscanf(in, "%lld", &N);

	if (N == 0 || N == -1)	  // Проверка на N = 0 или пустой файл
	{
		fclose(in);
		if (N == 0)
		{
			printf("N = 0, we cannot solve a system of linear equations without linear equations");
			return ERROR_INVALID_PARAMETER;
		}
		printf("nothing in input file");
		return ERROR_INVALID_DATA;
	}
	float **expandedMatrix;
	expandedMatrix = (float **)malloc(N * sizeof(float));
	if (expandedMatrix == NULL)
	{
		fclose(in);
		printf("N is too big, not enough memory to create lines for linear equations");
		return ERROR_NOT_ENOUGH_MEMORY;
	}

	for (long long i = 0; i < N; i++)
	{
		expandedMatrix[i] = (float *)malloc((N + 1) * sizeof(float));
		if (expandedMatrix[i] == NULL)
		{
			fclose(in);
			printf("not enough memory to create line");
			free2DArray(N, expandedMatrix);
			return ERROR_NOT_ENOUGH_MEMORY;
		}
		for (long long j = 0; j < N + 1; j++)
		{
			fscanf(in, "%f", &expandedMatrix[i][j]);
		}
	}

	fclose(in);

	long long expandedMatrixRank = rankOfExpandedMatrix(N, N + 1, expandedMatrix);

	long long matrixRank = rankOfMatrix(N, N, expandedMatrix);

	int flag;
	if (matrixRank != expandedMatrixRank)
	{
		flag = 0;
	}
	else if (matrixRank == expandedMatrixRank && expandedMatrixRank < N)
	{
		flag = 2;
	}
	else
	{
		flag = 1;
	}
	FILE *out = fopen(argv[2], "w");
	if (!fileException(out))
	{
		free2DArray(N, expandedMatrix);
		return ERROR_FILE_NOT_FOUND;
	}

	int errorResult = gauss(N, flag, expandedMatrix, out);

	fclose(out);

	free2DArray(N, expandedMatrix);

	return errorResult;
}