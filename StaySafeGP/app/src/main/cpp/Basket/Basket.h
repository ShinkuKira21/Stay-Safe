/* Author: Edward Patch */

#include "BasketInformation.h"

class Basket
{
    protected:
        CBInformation* basket;

    public:
        Basket(std::string** data, int* size, std::string action = "ATB");
        ~Basket();
};