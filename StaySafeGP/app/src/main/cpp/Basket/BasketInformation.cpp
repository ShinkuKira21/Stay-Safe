#include "BasketInformation.h"

void CBInformation::SetCBInformation(std::string** data, std::string action)
{
    if(action == "standard")
    {
        //Rows
        for(int i = 0; i < sizeof(data) / sizeof(data[0]); i++)
        {
            //Cols
            for (int j = 0; j < sizeof(data[0]) / sizeof(int); i++)
            {

            }
        }
    }
}

void CBInformation::AllocatePointers()
{
    //Declares and initialises the following variables
    int rowSize = 0;
    int colSize = 0;

    //If staticProducts is not equal to nullptr
    //Retrieve sizes
    if (staticProducts != nullptr)
    {
        rowSize = sizeof(staticProducts) / sizeof(staticProducts[0]);
        colSize = sizeof(staticProducts[0]) / sizeof(int);

        rowSize += 1;
        colSize += 1;
    }

    //Allocate Products Pointer
    products = new std::string*[colSize];

    for(int i = 0; i < rowSize; i++)
        products[i] = new std::string[rowSize];
}

void CBInformation::DeallocatePointers()
{
    int rowSize = 0;

    //Safety Check
    if(products != nullptr)
    {
        rowSize = sizeof(products) / sizeof(products[0]);

        //Cleans pointers
        delete[] products;
        for(int i = 0; i < rowSize; i++)
            delete[] products[i];
    }
}