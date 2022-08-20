#include "quicksort.h"
#include "phonebook.h"
#include <algorithm>

#define ll long long

template void quicksort< int, true >(int* arr, ll l, ll r);
template void quicksort< float, true >(float* arr, ll l, ll r);
template void quicksort< phonebook, true >(phonebook* arr, ll l, ll r);
template void quicksort< int, false >(int* arr, ll l, ll r);
template void quicksort< float, false >(float* arr, ll l, ll r);
template void quicksort< phonebook, false >(phonebook* arr, ll l, ll r);

template< typename T, bool descending >
void quicksort(T* arr, ll l, ll r)
{
	while (l < r)
	{
		ll i = l;
		ll j = r;
		ll x;
		T mid = arr[(i + j) / 2];
		while (true)
		{
			if (descending)
			{
				while (more(arr[i], mid))
				{
					i++;
				}
				while (less(arr[j], mid))
				{
					j--;
				}
			}
			else
			{
				while (less(arr[i], mid))
				{
					i++;
				}
				while (more(arr[j], mid))
				{
					j--;
				}
			}
			if (i >= j)
			{
				x = j;
				break;
			}
			std::swap(arr[i++], arr[j--]);
		}

		if (l - x > x - r)
		{
			quicksort< T, descending >(arr, l, x);
			l = x + 1;
		}
		else
		{
			quicksort< T, descending >(arr, x, r);
			r = x;
		}
	}
}

template<>
bool less< phonebook >(phonebook x, phonebook y)
{
	return ifLess(x, y);
}

template<>
bool more< phonebook >(phonebook x, phonebook y)
{
	return ifMore(x, y);
}

template<>
bool less< float >(float x, float y)
{
	if (x < y)
	{
		return true;
	}
	return false;
}

template<>
bool more< float >(float x, float y)
{
	if (x > y)
	{
		return true;
	}
	return false;
}

template<>
bool less< int >(int x, int y)
{
	if (x < y)
	{
		return true;
	}
	return false;
}

template<>
bool more< int >(int x, int y)
{
	if (x > y)
	{
		return true;
	}
	return false;
}