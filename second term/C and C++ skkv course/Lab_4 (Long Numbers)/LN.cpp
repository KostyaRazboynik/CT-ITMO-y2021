#include "LN.h"

using namespace std;

/// удаление ведущих нулей
void LN::skipWhiteSpace()
{
	while (m_digits.size() > 1 && !m_digits.back())
		m_digits.pop_back();
}

/// конструктор копирования
LN::LN(const LN &a)
{
	this->m_NaN = a.m_NaN;
	this->m_neg = a.m_neg;
	this->m_broke = a.m_broke;
	try
	{
		this->m_digits = a.m_digits;
	} catch (bad_alloc)
	{
		this->m_broke = true;
	}
}

/// оператор копирвоания
void LN::operator=(const LN &a)
{
	this->m_NaN = a.m_NaN;
	this->m_neg = a.m_neg;
	this->m_broke = a.m_broke;
	try
	{
		this->m_digits = a.m_digits;
	} catch (bad_alloc)
	{
		this->m_broke = true;
	}
}

/// конструктор перемещения
LN::LN(LN &&a)
{
	this->m_NaN = a.m_NaN;
	this->m_neg = a.m_neg;
	this->m_broke = a.m_broke;
	try
	{
		this->m_digits = a.m_digits;
	} catch (bad_alloc)
	{
		this->m_broke = true;
	}
	a.m_NaN = a.m_neg = a.m_broke = false;
	a.m_digits.clear();
}

/// ператор перемещения
void LN::operator=(LN &&a)
{
	this->m_NaN = a.m_NaN;
	this->m_neg = a.m_neg;
	this->m_broke = a.m_broke;
	try
	{
		this->m_digits = a.m_digits;
	} catch (bad_alloc)
	{
		this->m_broke = true;
	}
	a.m_NaN = a.m_neg = a.m_broke = false;
	a.m_digits.clear();
}

/// из ll со значением по умолчанию: 0
LN::LN(long long l)
{
	m_broke = false;
	m_NaN = false;
	m_neg = false;
	if (l < 0)
		m_neg = true;
	if (l == 0)
	{
		try
		{
			m_digits.push_back(0);
		} catch (bad_alloc)
		{
			m_broke = true;
		}
	}
	else
	{
		while (l != 0)
		{
			try
			{
				m_digits.push_back(abs(l % 10));
				l /= 10;
			} catch (bad_alloc)
			{
				m_broke = true;
				break;
			}
		}
	}
}

/// из строки символов C
LN::LN(const char *str)
{
	*this = LN(string(str));
}

/// из строки std::string_view
LN::LN(string_view str)
{
	m_NaN = false;
	m_broke = false;
	if (str == "NaN")
	{
		m_NaN = true;
		m_neg = false;
	}
	else
	{
		int flag = 0;
		if (str[0] == '-')
		{
			flag = 1;
			m_neg = true;
		}
		else
			m_neg = false;

		for (int i = str.length() - 1; i >= flag; i--)
		{
			try
			{
				m_digits.push_back(str[i] - '0');
			} catch (bad_alloc)
			{
				m_broke = true;
				break;
			}
		}
		skipWhiteSpace();
	}
}

/// преобразование в bool
LN::operator bool() const
{
	return toString() == "0";
}

/// преобразование в long long
LN::operator long long() const
{
	if (*this > 9223372036854775807_ln || *this < -9223372036854775808_ln)
		throw overflow_error("number is too big for long long");
	else
		return atoll(toString().c_str());
}

///                    ==
const bool operator==(const LN &a, const LN &b)
{
	if (a.m_NaN || b.m_NaN)
		return false;
	if (a.m_neg != b.m_neg)
		return false;
	if (a.m_digits.size() != b.m_digits.size())
		return false;
	for (int i = 0; i < a.m_digits.size(); ++i)
	{
		if (a.m_digits[i] != b.m_digits[i])
			return false;
	}
	return true;
}

///                    !=
const bool operator!=(const LN &a, const LN &b)
{
	if (a.m_NaN || b.m_NaN)
		return true;
	return !(a == b);
}

///                    <
const bool operator<(const LN &a, const LN &b)
{
	if (a.m_NaN || b.m_NaN)
		return false;
	if (a == b)
		return false;
	if (a.m_neg)
	{
		if (b.m_neg)
			return ((-a) > (-b));
		else
			return true;
	}
	else if (b.m_neg)
		return false;
	else
	{
		if (a.m_digits.size() != b.m_digits.size())
			return a.m_digits.size() < b.m_digits.size();
		else
		{
			for (int i = a.m_digits.size() - 1; i >= 0; --i)
			{
				if (a.m_digits[i] != b.m_digits[i])
					return a.m_digits[i] < b.m_digits[i];
			}
			return false;
		}
	}
}

///                    >
const bool operator>(const LN &a, const LN &b)
{
	if (a.m_NaN || b.m_NaN)
		return false;
	return !(a == b || a < b);
}

///                    <=
const bool operator<=(const LN &a, const LN &b)
{
	if (a.m_NaN || b.m_NaN)
		return false;
	return !(b < a);
}

///                    >=
const bool operator>=(const LN &a, const LN &b)
{
	if (a.m_NaN || b.m_NaN)
		return false;
	return !(b > a);
}

///                    +
const LN operator+(const LN &a, const LN &b)
{
	LN result;
	if (a.m_NaN || b.m_NaN)
	{
		result.m_NaN = true;
		return result;
	}
	if (a.m_neg)
	{
		if (b.m_neg)
			return a - (-b);
		else
			return b - (-a);
	}
	else if (b.m_neg)
		return a - (-b);
	int carry = 0;
	try
	{
		result.m_digits.resize(max(b.m_digits.size(), a.m_digits.size()) + 1, 0);
	} catch (bad_alloc)
	{
		result.m_broke = true;
		return result;
	}
	for (int i = 0; i < max(a.m_digits.size(), b.m_digits.size()) || carry != 0; ++i)
	{
		if (i < a.m_digits.size())
			result.m_digits[i] += a.m_digits[i];

		if (i < b.m_digits.size())
			result.m_digits[i] += b.m_digits[i];

		result.m_digits[i] += carry;

		if (result.m_digits[i] >= 10)
		{
			carry = 1;
			result.m_digits[i] %= 10;
			continue;
		}
		carry = 0;
	}
	result.skipWhiteSpace();
	return result;
}

///                    +=
LN &LN::operator+=(const LN &a)
{
	*this = (*this + a);
	return *this;
}

///                    унарный минус
LN LN::operator-() const
{
	LN tmp(*this);
	if (tmp.toString() == "0")
	{
		tmp.m_neg = false;
		return tmp;
	}
	tmp.m_neg = !tmp.m_neg;
	return tmp;
}

///                    -
const LN operator-(const LN &a, const LN &b)
{
	LN result;
	if (a.m_NaN || b.m_NaN)
	{
		result.m_NaN = true;
		return result;
	}
	if (b.m_neg)
		return a + (-b);
	else if (a.m_neg)
		return -(-a + b);
	else if (a < b)
		return -(b - a);
	if (b == LN(0ll))
		return a;
	int carry = 0;
	try
	{
		result.m_digits.resize(a.m_digits.size(), 0);
	} catch (bad_alloc)
	{
		result.m_broke = true;
		return result;
	}
	for (int i = 0; i < a.m_digits.size(); ++i)
		result.m_digits[i] = a.m_digits[i];
	for (int i = 0; i < b.m_digits.size() || carry != 0; ++i)
	{
		result.m_digits[i] -= carry;

		if (i < b.m_digits.size())
		{
			result.m_digits[i] -= b.m_digits[i];
		}

		if (result.m_digits[i] < 0)
		{
			carry = 1;
			result.m_digits[i] += 10;
			continue;
		}
		carry = 0;
	}

	result.skipWhiteSpace();
	return result;
}

///                    -=
LN &LN::operator-=(const LN &a)
{
	*this = (*this - a);
	return *this;
}

///                    *
const LN operator*(const LN &a, const LN &b)
{
	LN result;
	if (a.m_NaN || b.m_NaN)
	{
		result.m_NaN = true;
		return result;
	}
	LN null = LN(0ll);
	if (a == null || b == null)
	{
		return null;
	}
	try
	{
		result.m_digits.resize(a.m_digits.size() + b.m_digits.size(), 0);
	} catch (bad_alloc)
	{
		result.m_broke = true;
		return result;
	}
	for (int i = 0; i < a.m_digits.size(); ++i)
	{
		int carry = 0;
		for (int j = 0; j < b.m_digits.size(); ++j)
		{
			int tmp = result.m_digits[i + j] + a.m_digits[i] * (j < b.m_digits.size() ? b.m_digits[j] : 0) + carry;
			result.m_digits[i + j] = (tmp % 10);
			carry = (tmp / 10);
		}
		int j = b.m_digits.size();
		while (carry)
		{
			result.m_digits[i + j] = carry % 10;
			j++;
			carry /= 10;
		}
	}

	result.m_neg = (a.m_neg != b.m_neg);
	result.skipWhiteSpace();
	return result;
}

///                    *=
LN &LN::operator*=(const LN &a)
{
	*this = *this * a;
	return *this;
}

///                    /
const LN operator/(const LN &a, const LN &b)
{
	LN result;
	LN null = LN(0LL);
	if (a.m_NaN || b.m_NaN || b == null)
	{
		result.m_NaN = true;
		return result;
	}
	LN x(a);
	LN y(b);
	x.m_neg = false;
	y.m_neg = false;
	if (x < y)
	{
		return null;
	}
	try
	{
		result.m_digits.resize(a.m_digits.size() - b.m_digits.size() + 1, 0);
	} catch (bad_alloc)
	{
		result.m_broke = true;
		return result;
	}

	for (int i = result.m_digits.size() - 1; i >= 0; --i)
	{
		while (y * result <= x)
		{
			result.m_digits[i]++;
		}
		result.m_digits[i]--;
	}

	result.m_neg = (a.m_neg != b.m_neg);
	result.skipWhiteSpace();
	return result;
}

///                    /=
LN &LN::operator/=(const LN &a)
{
	*this = (*this / a);
	return *this;
}

///                    %
const LN operator%(const LN &a, const LN &b)
{
	LN result;
	if (a.m_NaN || b.m_NaN || b == LN(0ll))
	{
		result.m_NaN = true;
		return result;
	}

	LN x(a);
	LN y(b);
	x.m_neg = false;
	y.m_neg = false;

	result = x - (x / y) * y;
	if (a.m_neg)
		result.m_neg = true;
	else
		result.m_neg = false;
	result.skipWhiteSpace();
	return result;
}

///                    %=
LN &LN::operator%=(const LN &a)
{
	*this = (*this % a);
	return *this;
}

///                    ~
const LN operator~(const LN &a)
{
	LN result;
	if (a.m_NaN || a.m_neg)
	{
		result.m_NaN = true;
		return result;
	}

	LN null = LN(0ll);
	if (a == null)
		return null;

	try
	{
		result.m_digits.resize(a.m_digits.size(), 0);
	} catch (bad_alloc)
	{
		result.m_broke = true;
		return result;
	}
	for (int i = result.m_digits.size() - 1; i >= 0; --i)
	{
		while ((result * result) <= a)
		{
			result.m_digits[i]++;
		}
		result.m_digits[i]--;
	}
	result.skipWhiteSpace();
	return result;
}

/// преобразование в строку
string LN::toString() const
{
	if (m_broke)
	{
		return "broke";
	}
	if (m_NaN)
	{
		return "NaN";
	}
	string result;
	if (m_neg)
	{
		result += "-";
	}

	char buffer[10];

	for (int i = m_digits.size() - 1; i >= 0; i--)
	{
		sprintf(buffer, "%d", m_digits[i]);
		result += buffer;
	}
	if (result == "-0")
	{
		return "0";
	}
	return result;
}