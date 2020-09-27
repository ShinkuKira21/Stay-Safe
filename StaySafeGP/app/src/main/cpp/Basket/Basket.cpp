/* Author: Edward Patch */

#include "Basket.h"

Basket::Basket(std::string** data, std::string action)
{
    //THIS WHOLE CLASS SEEMS WASTEFUL ATM
    CBInformation* cb = new CBInformation(data, action);
}

Basket::~Basket()
{

}