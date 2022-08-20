#include "return_codes.h"

#include <malloc.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>

#define ZLIB

#if defined ISAL
	#include <include/igzip_lib.h>
#elif defined LIBDEFLATE
	#include <libdeflate.h>
#elif defined ZLIB
	#include <zlib.h>
#endif

int fileException(FILE *file)	 // проверка наличия файла
{
	if (file == NULL)
	{
		fprintf(stderr, "file not found\n");
		return 0;
	}
	return 1;
}

int equalityCheck(const int *a, const unsigned char *b, int ind)
{
	for (int i = 0; i < ind; i++)
	{
		if (a[i] != b[i])
		{
			return 0;
		}
	}
	return 1;
}

int collorCount(int type)
{
	if (type == 0)
	{
		return 1;
	}
	return 3;
}

int main(int argc, char **argv)
{
	if (argc != 3)
	{
		fprintf(stderr, "incorrect number of command line arguments\n");
		return ERROR_INVALID_DATA;
	}

	FILE *in = fopen(argv[1], "rb");
	if (!fileException(in))
	{
		ERROR_FILE_NOT_FOUND;
	}

	unsigned char *Check = (unsigned char *)malloc((8) * sizeof(unsigned char));
	if (Check == NULL)
	{
		fclose(in);
		fprintf(stderr, "not enough memory to crate data array\n");
		return ERROR_NOT_ENOUGH_MEMORY;
	}

	int high = 0;
	int width = 0;
	int bitDepth = 0;
	int colorType = 0;
	int compressionMethod = 0;
	int filteringMethod = 0;
	const int truePNG[] = { 137, 80, 78, 71, 13, 10, 26, 10 };
	const int trueIHDR[] = { 0, 0, 0, 13, 73, 72, 68, 82 };
	const int trueIDAT[] = { 73, 68, 65, 84 };
	const int trueIEND[] = { 73, 69, 78, 68 };

	for (int i = 0; i < 8; i++)
	{
		if (!fread(&Check[i], sizeof(unsigned char), 1, in))
		{
			fclose(in);
			free(Check);
			fprintf(stderr, "error while reading or file is damaged");
			return ERROR_UNKNOWN;
		}
	}

	if (!equalityCheck(truePNG, Check, 8))
	{
		fclose(in);
		free(Check);
		fprintf(stderr, "wrong png signature\n");
		return ERROR_INVALID_DATA;
	}

	for (int i = 0; i < 8; i++)
	{
		if (!fread(&Check[i], sizeof(unsigned char), 1, in))
		{
			fclose(in);
			free(Check);
			fprintf(stderr, "error while reading or file is damaged");
			return ERROR_UNKNOWN;
		}
	}

	if (!equalityCheck(trueIHDR, Check, 8))
	{
		free(Check);
		fclose(in);
		fprintf(stderr, "IHDR chunk isn't correct\n");
		return ERROR_INVALID_DATA;
	}

	for (int i = 0; i < 4; i++)
	{
		if (!fread(&Check[i], sizeof(unsigned char), 1, in))
		{
			fclose(in);
			free(Check);
			fprintf(stderr, "error while reading or file is damaged");
			return ERROR_UNKNOWN;
		}
		width = width * 16 * 16 + Check[i];
	}

	for (int i = 0; i < 4; i++)
	{
		if (!fread(&Check[i], sizeof(unsigned char), 1, in))
		{
			fclose(in);
			free(Check);
			fprintf(stderr, "error while reading or file is damaged");
			return ERROR_UNKNOWN;
		}
		high = high * 16 * 16 + Check[i];
	}

	free(Check);

	if (width == 0 || high == 0)
	{
		fclose(in);
		fprintf(stderr, "width and high must be > 0\n");
		return ERROR_INVALID_PARAMETER;
	}

	if (!fread(&bitDepth, sizeof(unsigned char), 1, in) || bitDepth != 8)
	{
		fclose(in);
		if (bitDepth != 8)
		{
			fprintf(stderr, "bit depth must be 8\n");
			return ERROR_INVALID_PARAMETER;
		}
		fprintf(stderr, "error while reading or file is damaged");
		return ERROR_UNKNOWN;
	}

	if (!fread(&colorType, sizeof(unsigned char), 1, in) || (colorType != 0 && colorType != 2))
	{
		fclose(in);
		if (colorType != 0 && colorType != 2)
		{
			fprintf(stderr, "color type must be 0 or 2\n");
			return ERROR_INVALID_PARAMETER;
		}
		fprintf(stderr, "error while reading or file is damaged");
		return ERROR_UNKNOWN;
	}

	int num = collorCount(colorType);

	if (!fread(&compressionMethod, sizeof(unsigned char), 1, in) || compressionMethod != 0)
	{
		fclose(in);
		if (compressionMethod != 0)
		{
			fprintf(stderr, "compression method must be 0\n");
			return ERROR_INVALID_PARAMETER;
		}
		fprintf(stderr, "error while reading or file is damaged");
		return ERROR_UNKNOWN;
	}

	if (!fread(&filteringMethod, sizeof(unsigned char), 1, in) || filteringMethod != 0)
	{
		fclose(in);
		if (filteringMethod != 0)
		{
			fprintf(stderr, "filtering method must be 0\n");
			return ERROR_INVALID_PARAMETER;
		}
		fprintf(stderr, "error while reading or file is damaged");
		return ERROR_UNKNOWN;
	}

	int trash = 0;
	for (int i = 0; i < 5; i++)
	{
		if (!fread(&trash, sizeof(unsigned char), 1, in))
		{
			fclose(in);
			fprintf(stderr, "error while reading or file is damaged");
			return ERROR_UNKNOWN;
		}
	}

	unsigned char *finalData = (unsigned char *)malloc((1000) * sizeof(unsigned char));
	if (finalData == NULL)
	{
		fclose(in);
		fprintf(stderr, "not enough memory to crate data array\n");
		return ERROR_NOT_ENOUGH_MEMORY;
	}
	long long finalSize = 1000;
	int currInd = 0;

	/** Считывание и обработка всего файла после IHDR */

	int countIDAT = 0;
	int countTrash = 0;

	while (1)
	{
		long long size = 0;

		for (int i = 0; i < 4; i++)
		{
			if (!fread(&trash, sizeof(unsigned char), 1, in))
			{
				fclose(in);
				free(finalData);
				fprintf(stderr, "error while reading or file is damaged");
				return ERROR_UNKNOWN;
			}
			size = size * 16 * 16 + trash;
		}

		unsigned char *name = (unsigned char *)malloc((4) * sizeof(unsigned char));
		if (name == NULL)
		{
			free(finalData);
			fclose(in);
			fprintf(stderr, "not enough memory to crate data array\n");
			return ERROR_NOT_ENOUGH_MEMORY;
		}

		for (int i = 0; i < 4; i++)
		{
			if (!fread(&name[i], sizeof(unsigned char), 1, in))
			{
				fclose(in);
				free(name);
				free(finalData);
				fprintf(stderr, "error while reading or file is damaged");
				return ERROR_UNKNOWN;
			}
		}

		int flagIDAT = equalityCheck(trueIDAT, name, 4);

		int flagIEND = equalityCheck(trueIEND, name, 4);

		free(name);

		if (flagIDAT == 1) /** блок обработки IDAT */
		{
			if (countTrash != 0)
			{
				fclose(in);
				free(finalData);
				fprintf(stderr, "chunk(s) between IDAT chunks\n");
				return ERROR_INVALID_DATA;
			}

			if (currInd + size > finalSize)
			{
				unsigned char *p = realloc(finalData, (finalSize + size) * 2);
				if (p != NULL)
				{
					finalData = p;
					finalSize = (finalSize + size) * 2;
				}
				else
				{
					fclose(in);
					free(finalData);
					fprintf(stderr, "not enough memory to read data\n");
					return ERROR_NOT_ENOUGH_MEMORY;
				}
			}

			for (int i = 0; i < size; i++)
			{
				if (!fread(&finalData[currInd++], sizeof(unsigned char), 1, in))
				{
					fclose(in);
					free(finalData);
					fprintf(stderr, "error while reading or file is damaged");
					return ERROR_UNKNOWN;
				}
			}
			for (int i = 0; i < 4; i++)
			{
				if (!fread(&trash, sizeof(unsigned char), 1, in))
				{
					fclose(in);
					free(finalData);
					fprintf(stderr, "error while reading or file is damaged");
					return ERROR_UNKNOWN;
				}
			}
			countIDAT++;
		}
		else if (flagIEND == 1)
		{
			break;
		}
		else
		{
			if (countIDAT != 0)
			{
				countTrash++;
			}
			for (int i = 0; i < size + 4; i++)
			{
				if (!fread(&trash, sizeof(unsigned char), 1, in))
				{
					fclose(in);
					free(finalData);
					fprintf(stderr, "error while reading or file is damaged");
					return ERROR_UNKNOWN;
				}	 // пропускаю все, что не IDAT
			}
		}
	}

	fclose(in);

	if (countIDAT == 0)
	{
		free(finalData);
		fprintf(stderr, "no IDAT chunk\n");
		return ERROR_INVALID_DATA;
	}

	/** Декомпрессия */
	unsigned char *decompress_buff = (unsigned char *)malloc((num * width * high + high) * sizeof(unsigned char));
	if (decompress_buff == NULL)
	{
		free(finalData);
		fprintf(stderr, "not enough memory to crate data array\n");
		return ERROR_NOT_ENOUGH_MEMORY;
	}

#ifdef ZLIB
	uLong decompressed_size = num * width * high + high;
	int uncompressResult = uncompress((Bytef *)decompress_buff, &decompressed_size, (Bytef *)finalData, (uLong)finalSize);

	free(finalData);

	if (uncompressResult != Z_OK)
	{
		free(decompress_buff);
		fprintf(stderr, "compressing problem\n");
		return ERROR_UNKNOWN;
	}
#elif defined LIBDEFLATE
	size_t decompressed_size = num * width * high + high;
	int uncompressResult =
		libdeflate_zlib_decompress(libdeflate_alloc_decompressor(), finalData, currInd, decompress_buff, decompressed_size, &decompressed_size);

	free(finalData);

	if (uncompressResult != LIBDEFLATE_SUCCESS)
	{
		free(decompress_buff);
		fprintf(stderr, "compressing problem\n");
		return ERROR_UNKNOWN;
	}
#elif defined ISAL
	struct inflate_state state;
	isal_inflate_init(&state);
	state.crc_flag = ISAL_ZLIB;
	state.next_out = decompress_buff;
	state.avail_out = num * width * high + high;
	state.next_in = finalData;
	state.avail_in = currInd;

	int uncompressResult = isal_inflate(&state);

	free(finalData);

	if (uncompressResult != ISAL_DECOMP_OK)
	{
		free(decompress_buff);
		fprintf(stderr, "compressing problem\n");
		return ERROR_UNKNOWN;
	}
#endif

	/** Дефильтрация */
	for (int i = 0; i < high; i++)
	{
		int filterType = decompress_buff[(width * num + 1) * i];
		if (filterType == 1)
		{
			for (int j = num + 1; j < width * num + 1; j++)
			{
				decompress_buff[i * (width * num + 1) + j] += decompress_buff[i * (width * num + 1) + j - num];
			}
		}
		else if (filterType == 2 && i != 0)
		{
			for (int j = 1; j < width * num + 1; j++)
			{
				decompress_buff[i * (width * num + 1) + j] += decompress_buff[(i - 1) * (width * num + 1) + j];
			}
		}
		else if (filterType == 3)
		{
			for (int j = 1; j < width * num + 1; j++)
			{
				if (i == 0)
				{
					if (j > num + 1)
					{
						decompress_buff[i * (width * num + 1) + j] +=
							(unsigned char)floor(decompress_buff[i * (width * num + 1) + j - num] / 2);
					}
					continue;
				}
				if (j < num + 1)
				{
					decompress_buff[i * (width * num + 1) + j] +=
						(unsigned char)floor(decompress_buff[(i - 1) * (width * num + 1) + j] / 2);
					continue;
				}
				unsigned char a = decompress_buff[i * (width * num + 1) + j - num];
				unsigned char b = decompress_buff[(i - 1) * (width * num + 1) + j];
				decompress_buff[i * (width * num + 1) + j] += (unsigned char)floor((a + b) / 2);
			}
		}
		else if (filterType == 4)
		{
			for (int j = 1; j < width * num + 1; j++)
			{
				if (i == 0)
				{
					if (j > num + 1)
					{
						decompress_buff[i * (width * num + 1) + j] += decompress_buff[i * (width * num + 1) + j - num];
					}
					continue;
				}
				if (j < num + 1)
				{
					decompress_buff[i * (width * num + 1) + j] += decompress_buff[(i - 1) * (width * num + 1) + j];
					continue;
				}
				unsigned char x;
				unsigned char a = decompress_buff[i * (width * num + 1) + j - num];
				unsigned char b = decompress_buff[(i - 1) * (width * num + 1) + j];
				unsigned char c = decompress_buff[(i - 1) * (width * num + 1) + j - num];
				int p = a + b - c;
				int pa = abs(p - a);
				int pb = abs(p - b);
				int pc = abs(p - c);

				if (pa <= pb && pa <= pc)
				{
					x = a;
				}
				else if (pb <= pc)
				{
					x = b;
				}
				else
				{
					x = c;
				}
				decompress_buff[i * (width * num + 1) + j] += x;
			}
		}
	}

	FILE *out = fopen(argv[2], "wb");
	if (!fileException(out))
	{
		free(decompress_buff);
		return ERROR_FILE_NOT_FOUND;
	}

	if (colorType == 0)
	{
		fprintf(out, "P5\n%i %i\n255\n", width, high);
	}
	else
	{
		fprintf(out, "P6\n%i %i\n255\n", width, high);
	}

	for (int i = 0; i < high; i++)
	{
		for (int j = 1; j < (width * num) + 1; j++)
		{
			if (!fwrite(&decompress_buff[i * (width * num + 1) + j], sizeof(unsigned char), 1, out))
			{
				fclose(out);
				free(decompress_buff);
				fprintf(stderr, "error while writing");
				return ERROR_UNKNOWN;
			}
		}
	}

	fclose(out);
	free(decompress_buff);

	return ERROR_SUCCESS;
}