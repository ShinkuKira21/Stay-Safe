#include "BasketInformation.h"

CBInformation::CBInformation(std::string** data, int* size, std::string action)
{
    SizeOfPointers(size); // Sets the Size of the Pointers
    AllocatePointers(); // Allocate the Pointers
    SetTempProducts(); // Sets TempProducts to the Products almost made.

    //Adds extra products to the existing products
    int dIndex = 0;
    for (int i = rStart; i < tmpSize; i++) //rStart changes if more products added
    {
        for (int j = 0; j < cSize; j++)
            tmpProducts[j][i] = data[j][dIndex];// add products to tmpProducts
           
        dIndex++;
    }
       
    productCount++; // adds another product
    products = tmpProducts; // Sets tmpProducts to products.
}

CBInformation::~CBInformation()
{
    DeallocatePointers(); // deallocate the pointers (memory management)
}

std::string** CBInformation::GetCBInformation(std::string action)
{
    if (action == "standard") return products; //returns products
    return nullptr; // returns nullptr
}

void CBInformation::SetTempProducts()
{
    //This will set products to tmpProducts
    for (int i = 0; i < productCount; i++)
        for(int j = 0; j < cSize; j++)
            tmpProducts[j][i] = products[j][i];
}

void CBInformation::AllocatePointers()
{
    //Retrieve sizes
    tmpSize = rSize;

    //if productCount is not 0, set new tmpSize and rStart.
    if (productCount != 0)
    {
        tmpSize = productCount + 1;
        rStart = productCount; // add 1 to each variable
    }

    //Allocates pointer
    tmpProducts = new std::string * [cSize];
    for (int i = 0; i < cSize; i++)
        tmpProducts[i] = new std::string[tmpSize];
}

void CBInformation::DeallocatePointers()
{
    //Cleans pointers
    delete[] tmpProducts;
    for (int i = 0; i < cSize; i++)
        delete[] tmpProducts[i];
}

void CBInformation::SizeOfPointers(int* size)
{
    //Sets row and col sizes.
    rSize = size[0];
    cSize = size[1];
}