/* Author: Edward Patch */

#include "Handler.h"

Handler::Handler(std::string** data, int* size, std::string action)
{
    basket = new CBInformation(data, size, action);

    // Record Login Details
    if(action == "RLD")
    {
        rowCount = size[0];
        colCount = size[1];
        accountDetails = data;
    }
}

Handler::~Handler() { }