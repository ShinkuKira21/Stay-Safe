/* Author: Edward Patch */

#include "Basket.h"

Basket::Basket(std::string** data, int* size, std::string action)
{
    //THIS WHOLE CLASS SEEMS WASTEFUL ATM
    basket = new CBInformation(data, size, action);
}

Basket::~Basket() { }