#include "phonebook.h"
#include <cstring>

int compare(char* x, char* y)
{
	int res = strcmp(x, y);
	if (res == 0)
	{
		return -1;
	}
	else if (res < 0)
	{
		return 1;
	}
	return 0;
}

bool ifMore(phonebook x, phonebook y)
{
	int comp = compare(x.m_surname, y.m_surname);
	if (comp != -1)
	{
		return !comp;
	}

	comp = compare(x.m_name, y.m_name);
	if (comp != -1)
	{
		return !comp;
	}

	comp = compare(x.m_patronymic, y.m_patronymic);
	if (comp != -1)
	{
		return !comp;
	}

	if (x.m_phoneNumber <= y.m_phoneNumber)
	{
		return false;
	}
	return true;
}

bool ifLess(phonebook x, phonebook y)
{
	int comp = compare(x.m_surname, y.m_surname);
	if (comp != -1)
	{
		return comp;
	}

	comp = compare(x.m_name, y.m_name);
	if (comp != -1)
	{
		return comp;
	}

	comp = compare(x.m_patronymic, y.m_patronymic);
	if (comp != -1)
	{
		return comp;
	}

	if (x.m_phoneNumber < y.m_phoneNumber)
	{
		return true;
	}
	return false;
}