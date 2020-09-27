#include "BasketInformation.cpp"
#include <vector>

std::string** CBInformation::products;

int main()
{
	std::vector< std::vector<std::string> > test;
	std::string** hello;

	hello = new std::string*[3];

	for (int i = 0; i < 3; i++)
	{
		hello[i] = new std::string[3];

		for (int j = 0; j < 3; j++)
			getline(std::cin, hello[i][j]);
	}

	std::cout << "Row: " << sizeof(hello);
	std::cout << "Col: " << sizeof(hello[0]);

	//CBInformation* cb = new CBInformation(hello, "Add");

	/*std::string** test = CBInformation::GetCBInformation();

	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			std::cout << test[i][j];*/

	delete[] hello;

	for (int i = 0; i < 3; i++)
		delete[] hello[i];

	return 0;
}