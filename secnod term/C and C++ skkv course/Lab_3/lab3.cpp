#include <cstring>
#include <iostream>

#include "return_codes.h"

#include "phonebook.h"
#include "quicksort.h"

#define ll long long

using namespace std;

int fileException(const FILE *file)	   // проверка наличия файла
{
	if (file == nullptr)
	{
		fprintf(stderr, "file not found\n");
		return 0;
	}
	return 1;
}

int readSortMode(FILE *in)
{
	char sortMode[11];
	if (fscanf(in, "%s", sortMode) != 1)
	{
		fprintf(stderr, "invalid data\n");
		return -1;
	}
	if (strcmp(sortMode, "ascending") == 0)
	{
		return 0;
	}
	else
	{
		return 1;
	}
}

template< typename T >
int readINTorFLOAT(const char *readFormat, const char *writeFormat, FILE *in, const char *filename)
{
	int sortModeFlag = readSortMode(in);
	if (sortModeFlag == -1)
	{
		return ERROR_INVALID_DATA;
	}

	ll N;
	if (fscanf(in, "%lld", &N) != 1)
	{
		fprintf(stderr, "invalid data\n");
		return ERROR_INVALID_DATA;
	}

	T *arr = (T *)malloc(N * sizeof(T));
	if (arr == nullptr)
	{
		fprintf(stderr, "N is too big\n");
		return ERROR_NOT_ENOUGH_MEMORY;
	}

	for (ll i = 0; i < N; i++)
	{
		if (fscanf(in, readFormat, &arr[i]) != 1)
		{
			fprintf(stderr, "invalid data\n");
			free(arr);
			return ERROR_INVALID_DATA;
		}
	}

	if (sortModeFlag)
	{
		quicksort< T, true >(arr, 0, N - 1);
	}
	else
	{
		quicksort< T, false >(arr, 0, N - 1);
	}

	FILE *out = fopen(filename, "w");
	if (!fileException(out))
	{
		free(arr);
		return ERROR_FILE_NOT_FOUND;
	}

	for (ll i = 0; i < N; i++)
	{
		fprintf(out, writeFormat, arr[i]);
		fprintf(out, "\n");
	}

	fclose(out);
	free(arr);

	return ERROR_SUCCESS;
}

int readPhonebook(FILE *in, const char *filename)
{
	int sortModeFlag = readSortMode(in);
	if (sortModeFlag == -1)
	{
		return ERROR_INVALID_DATA;
	}

	ll N;
	if (fscanf(in, "%lld", &N) != 1)
	{
		fprintf(stderr, "invalid data\n");
		return ERROR_INVALID_DATA;
	}

	phonebook *arr = (phonebook *)malloc(N * sizeof(phonebook));
	if (arr == nullptr)
	{
		fprintf(stderr, "N is too big\n");
		return ERROR_NOT_ENOUGH_MEMORY;
	}

	for (ll i = 0; i < N; i++)
	{
		if (fscanf(in, "%s", arr[i].m_surname) != 1)
		{
			fprintf(stderr, "invalid data\n");
			return ERROR_INVALID_DATA;
		}

		if (fscanf(in, "%s", arr[i].m_name) != 1)
		{
			fprintf(stderr, "invalid data\n");
			return ERROR_INVALID_DATA;
		}

		if (fscanf(in, "%s", arr[i].m_patronymic) != 1)
		{
			fprintf(stderr, "invalid data\n");
			return ERROR_INVALID_DATA;
		}

		if (fscanf(in, "%lld", &arr[i].m_phoneNumber) != 1)
		{
			fprintf(stderr, "invalid data\n");
			return ERROR_INVALID_DATA;
		}
	}

	if (sortModeFlag)
	{
		quicksort< phonebook, true >(arr, 0, N - 1);
	}
	else
	{
		quicksort< phonebook, false >(arr, 0, N - 1);
	}

	FILE *out = fopen(filename, "w");
	if (!fileException(out))
	{
		free(arr);
		return ERROR_FILE_NOT_FOUND;
	}

	for (ll i = 0; i < N; i++)
	{
		fprintf(out, "%s %s %s %lld\n", arr[i].m_surname, arr[i].m_name, arr[i].m_patronymic, arr[i].m_phoneNumber);
	}

	fclose(out);
	free(arr);

	return ERROR_SUCCESS;
}

int main(int argc, char **argv)
{
	if (argc != 3)
	{
		fprintf(stderr, "incorrect number of command line arguments\n");
		return ERROR_INVALID_DATA;
	}

	FILE *in = fopen(argv[1], "r");
	if (!fileException(in))
	{
		ERROR_FILE_NOT_FOUND;
	}

	char type[10];
	if (fscanf(in, "%s", type) != 1)
	{
		fclose(in);
		fprintf(stderr, "invalid data\n");
		return ERROR_INVALID_DATA;
	}

	int readingResult = ERROR_SUCCESS;
	if (strcmp(type, "int") == 0)
	{
		readingResult = readINTorFLOAT< int >("%i", "%i", in, argv[2]);
	}
	else if (strcmp(type, "float") == 0)
	{
		readingResult = readINTorFLOAT< float >("%f", "%g", in, argv[2]);
	}
	else if (strcmp(type, "phonebook") == 0)
	{
		readingResult = readPhonebook(in, argv[2]);
	}
	else
	{
		fprintf(stderr, "incorrect type\n");
		fclose(in);
		return ERROR_INVALID_DATA;
	}

	fclose(in);

	return readingResult;
}