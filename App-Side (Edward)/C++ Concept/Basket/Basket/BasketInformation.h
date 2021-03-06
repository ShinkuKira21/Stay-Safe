/* Author: Edward Patch */

#include <iostream>
#include <string>
#include <sstream>

class CBInformation
{
    protected:
        //rowSize  columnSize
        int rSize, cSize;
        int rStart = 0, tmpSize;
        static int productCount;

        //Products - [Product Name][ProductCols]
        std::string** tmpProducts;
        static std::string** products; // current products in basket

    public:
        CBInformation(std::string** data, int* size, std::string action);
        ~CBInformation();

        //GetCBInformation Declaration
        // Arguments (String, action, Default: "standard")
        static std::string** GetCBInformation(std::string action = "standard");
        static int GetProductRows() { return productCount; }

    private:
        void SetTempProducts(std::string** data, std::string action);
        void SetProducts(std::string** data, std::string action);
        void AllocatePointers(std::string action);
        void DeallocatePointers();
        void SizeOfPointers(int* size);
};