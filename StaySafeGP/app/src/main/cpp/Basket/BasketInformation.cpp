#include "BasketInformation.h"

CBInformation::CBInformation(std::string** data, std::string action)
{
    SizeOfPointers(data);
    AllocatePointers();

    for(int i = 0; i <= rSize; i++)
    {
        for(int j = 0; j <= cSize; j++)
        {
            // add products to tmpProducts
            if(i + 1 < rSize && j + 1 < cSize)
                tmpProducts[j][i] = products[j][i];

            // add data to tmpProducts
            // set tmpProducts to products
            else
            {
                tmpProducts[j][i] = data[j][i];
                products[j][i] = tmpProducts[j][i];
            }
        }
    }
}

CBInformation::~CBInformation()
{
    DeallocatePointers();
}

std::string** CBInformation::GetCBInformation(std::string action)
{
    if(action == "standard")
    {
        return products;
    }

    return nullptr;
}

void CBInformation::AllocatePointers()
{
    //Declares and initialises the following variables
    int rowSize = sizeof(products) / sizeof(products[0]);
    int colSize = sizeof(products[0]) / sizeof(int);

    //If rowSize is not equal to 0 and colSize is not equal to 0
    //Retrieve sizes
    if (rowSize != 0 && colSize != 0)
        rowSize += 1; colSize += 1; // add 1 to each variable

    //Allocate Products Pointer
    tmpProducts = new std::string*[colSize];

    for(int i = 0; i < rowSize; i++)
        tmpProducts[i] = new std::string[rowSize];
}

void CBInformation::DeallocatePointers()
{
    int rowSize = 0;

    //Safety Check
    if(tmpProducts != nullptr)
    {
        rowSize = sizeof(tmpProducts) / sizeof(tmpProducts[0]);

        //Cleans pointers
        delete[] tmpProducts;
        for(int i = 0; i < rowSize; i++)
            delete[] tmpProducts[i];
    }
}

void CBInformation::SizeOfPointers(std::string** data)
{
    rSize = sizeof(data) / sizeof(data[0]);
    cSize = sizeof(data[0]) / sizeof(int);
}