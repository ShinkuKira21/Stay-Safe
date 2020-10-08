/* Author: Edward Patch */

#include "../Basket/BasketInformation.h"

class Handler
{
    protected:
        CBInformation* basket;
        static std::string** accountDetails;
        static int rowCount;
        static int colCount;

    public:
        Handler(std::string** data, int* size, std::string action);
        ~Handler();

        static std::string** GetAccountDetails() { return accountDetails; }
        static int GetRowSize() { return rowCount; }
        static int GetColSize() { return colCount; }
};