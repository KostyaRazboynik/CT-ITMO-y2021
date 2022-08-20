struct phonebook
{
	char m_surname[21];
	char m_name[21];
	char m_patronymic[21];
	long long m_phoneNumber;
};

bool ifMore(phonebook x, phonebook y);
bool ifLess(phonebook x, phonebook y);