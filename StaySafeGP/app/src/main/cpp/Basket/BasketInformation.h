/* Author: Edward Patch */

#include "../functions.cpp"

class CBInformation
{
    protected:
        //rowSize  columnSize
        int rSize, cSize;

        //Products - [Product Name][ProductCols]
        std::string** tmpProducts = nullptr;
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