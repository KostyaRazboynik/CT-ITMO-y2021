#include <cstdlib>
#include <iostream>
#include <vector>

class LN
{
	bool m_neg;		 // negative
	bool m_NaN;		 // NaN?
	bool m_broke;	 // exception
	std::vector< char > m_digits;
	void skipWhiteSpace();

  public:
	LN(const LN &);
	LN(LN &&);
	LN(long long a = 0);
	LN(const char *);
	LN(std::string_view);

	void operator=(const LN &);
	void operator=(LN &&);

	operator bool() const;
	operator long long() const;

	friend const bool operator==(const LN &, const LN &);
	friend const bool operator!=(const LN &, const LN &);
	friend const bool operator<(const LN &, const LN &);
	friend const bool operator>(const LN &, const LN &);
	friend const bool operator<=(const LN &, const LN &);
	friend const bool operator>=(const LN &, const LN &);

	friend const LN operator+(const LN &, const LN &);
	LN &operator+=(const LN &);

	LN operator-() const;
	friend const LN operator-(const LN &, const LN &);
	LN &operator-=(const LN &);

	friend const LN operator*(const LN &, const LN &);
	LN &operator*=(const LN &);

	friend const LN operator/(const LN &, const LN &);
	LN &operator/=(const LN &);

	friend const LN operator%(const LN &, const LN &);
	LN &operator%=(const LN &);

	friend const LN operator~(const LN &);

	std::string toString() const;
};

inline LN operator"" _ln(const char *a)
{
	return LN(a);
}