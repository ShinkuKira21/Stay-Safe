#include "BasketInformation.h"

std::string** CBInformation::products;
int CBInformation::productCount;

int main()
{
	std::string** hello;
	int size[2] = { 1, 3 };


	hello = new std::string*[size[1]];

	for (int i = 0; i < size[1]; i++)
	{
		hello[i] = new std::string[size[0]];

		for (int j = 0; j < size[0]; j++)
			getline(std::cin, hello[i][j]);
	}

	CBInformation* cb = new CBInformation(hello, size, "Add");

	delete[] hello;

	hello = new std::string * [size[1]];

	for (int i = 0; i < size[1]; i++)
	{
		hello[i] = new std::string[size[0]];

		for (int j = 0; j < size[0]; j++)
			getline(std::cin, hello[i][j]);
	}

	cb = new CBInformation(hello, size, "Add");

	std::string** test = CBInformation::GetCBInformation();
	int newSize[2] = { CBInformation::GetProductRows(), 3 };

	for (int i = 0; i < newSize[0]; i++)
		for (int j = 0; j < newSize[1]; j++)
			std::cout << test[j][i];

	delete[] hello;

	return 0;
}