#include "BasketInformation.h"

CBInformation::CBInformation(std::string** data, int* size, std::string action)
{
    SizeOfPointers(size); // Sets the Size of the Pointers
    AllocatePointers(action); // Allocate the Pointers
    SetTempProducts(data, action); // Sets TempProducts to the Products almost made.
    SetProducts(data, action); // Sets tmpProducts to the Products (to new list)
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

void CBInformation::SetTempProducts(std::string** data, std::string action)
{
    //This will set products to tmpProducts
    if(action == "ATB")
        for (int i = 0; i < productCount; i++)
            for(int j = 0; j < cSize; j++)
                tmpProducts[j][i] = products[j][i];

    if (action == "RFB")
    {
        //Declares boolean named bRowMatched and set to false
        bool bRowMatched = false;

        int dIndex = 0;
        for (int i = 0; i < productCount; i++)
        {
            for (int j = 0; j < cSize; j++)
            {
                if (products[j][i] != data[j][0]) // Only add if unique
                {
                    bRowMatched = true; // set bRowMatched to true
                    tmpProducts[j][dIndex] = products[j][i]; //sets tmpProducts to products
                }
            }

            if (bRowMatched)
            {
                dIndex++; // adds to dIndex
                bRowMatched = false; // sets bRowMatched to false
            }
        }


    }
}

void CBInformation::SetProducts(std::string** data, std::string action)
{
    if (action == "ATB")
    {
        //Adds extra product to the existing products
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

    if (action == "RFB")
    {
        //Removes a product from existing products
        productCount--;
        products = tmpProducts;
    }
}

void CBInformation::AllocatePointers(std::string action)
{
    if (action == "ATB")
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
    if (action == "RFB")
    {
        //Safety Check
        if (productCount != 0)
        {
            tmpSize = productCount - 1;

            tmpProducts = new std::string * [cSize];
            for (int i = 0; i < cSize; i++)
                tmpProducts[i] = new std::string[tmpSize];
        }
    }
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
    colCount = size[1];
}