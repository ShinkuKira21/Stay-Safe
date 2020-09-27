/* Author: Edward Patch */

#include <iostream>
#include <string>

class CBInformation
{
    protected:
        //rowSize  columnSize
        int rSize, cSize;

        //Products - [Product Name][ProductCols]
        
        static std::string** products;

    public:
        CBInformation(std::string** data, std::string action);
        ~CBInformation();

        //GetCBInformation Declaration
        // Arguments (String, action, Default: "standard")
        static std::string** GetCBInformation(std::string action = "standard");

    private:
        void AllocatePointers();
        void DeallocatePointers();
        void SizeOfPointers(std::string** data);
};