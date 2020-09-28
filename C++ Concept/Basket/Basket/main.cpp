/* Author: Edward Patch */

//MY MESSY MAIN NOTEPAD.

#include "BasketInformation.h"

std::string** CBInformation::products;
int CBInformation::productCount;

int main()
{
	std::string** input;
	int size[2] = { 1, 3 };


	input = new std::string*[size[1]];

	for (int i = 0; i < size[1]; i++)
	{
		input[i] = new std::string[size[0]];

		for (int j = 0; j < size[0]; j++)
			getline(std::cin, input[i][j]);
	}

	CBInformation* cb = new CBInformation(input, size, "Add");

	delete[] input;

	input = new std::string * [size[1]];

	for (int i = 0; i < size[1]; i++)
	{
		input[i] = new std::string[size[0]];

		for (int j = 0; j < size[0]; j++)
			getline(std::cin, input[i][j]);
	}

	cb = new CBInformation(input, size, "Add");

	delete[] input;

	input = new std::string * [size[1]];

	for (int i = 0; i < size[1]; i++)
	{
		input[i] = new std::string[size[0]];

		for (int j = 0; j < size[0]; j++)
			getline(std::cin, input[i][j]);
	}

	cb = new CBInformation(input, size, "Remove");

	delete[] input;


	input = new std::string * [size[1]];

	for (int i = 0; i < size[1]; i++)
	{
		input[i] = new std::string[size[0]];

		for (int j = 0; j < size[0]; j++)
			getline(std::cin, input[i][j]);
	}

	cb = new CBInformation(input, size, "Add");

	delete[] input;

	std::string** output = CBInformation::GetCBInformation();
	int newSize[2] = { CBInformation::GetProductRows(), 3 };

	for (int i = 0; i < newSize[0]; i++)
		for (int j = 0; j < newSize[1]; j++)
			std::cout << output[j][i];

	return 0;
}