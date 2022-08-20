#include "LN.h"
#include "return_codes.h"

#include <fstream>
#include <functional>
#include <map>
#include <stack>
#include <string>

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

int main(int argc, char **argv)
{
	if (argc != 3)
	{
		fprintf(stderr, "incorrect number of command line arguments\n");
		return ERROR_INVALID_DATA;
	}

	ifstream in(argv[1]);
	if (!in)
	{
		fprintf(stderr, "file not found\n");
		return ERROR_FILE_NOT_FOUND;
	}

	map< string, function< LN(LN, LN) > > operations = {
		{ "+", plus() },		{ "+=", plus() },		   { "-", minus() },	  { "-=", minus() },
		{ "/", divides() },		{ "/=", divides() },	   { "*", multiplies() }, { "*=", multiplies() },
		{ "==", equal_to() },	{ "!=", not_equal_to() },  { ">", greater() },	  { "<", less() },
		{ "<=", less_equal() }, { ">=", greater_equal() }, { "%", modulus() },	  { "%=", modulus() }
	};

	string s;
	stack< LN > stack;
	LN a, b;
	while (getline(in, s))
	{
		LN psh;
		auto op = operations.find(s);
		if (op == operations.end())
		{
			if (s == "_" || s == "~")
			{
				a = stack.top();
				stack.pop();
				if (s == "_")
					psh = (-a);
				else
					psh = (~a);
			}
			else
				psh = LN(s);
		}
		else
		{
			b = stack.top();
			stack.pop();
			a = stack.top();
			stack.pop();
			psh = (op->second(a, b));
		}
		if (psh.toString() == "broke")
		{
			fprintf(stderr, "not enough memory\n");
			in.close();
			return ERROR_NOT_ENOUGH_MEMORY;
		}
		try
		{
			stack.push(psh);
		} catch (bad_alloc)
		{
			fprintf(stderr, "not enough memory\n");
			in.close();
			return ERROR_NOT_ENOUGH_MEMORY;
		}
	}
	in.close();

	FILE *out = fopen(argv[2], "w");
	if (!fileException(out))
	{
		return ERROR_FILE_NOT_FOUND;
	}

	while (!stack.empty())
	{
		a = stack.top();
		stack.pop();
		fprintf(out, "%s\n", a.toString().c_str());
	}
	fclose(out);
	return ERROR_SUCCESS;
}