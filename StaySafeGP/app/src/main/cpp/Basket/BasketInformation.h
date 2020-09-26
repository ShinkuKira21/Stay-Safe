/* Author: Edward Patch */

#include "../functions.cpp"

class CBInformation
{
    protected:
        //Products - [Product Name][ProductCols]
        std::string** products = nullptr;
        static std::string** staticProducts;

    public:
        //SetCBInformation Declaration
        // Arguments (2D Pointer String, data), (String, action, Default: "standard")
        static void SetCBInformation(std::string** data, std::string action = "standard");

    private:
        void AllocatePointers();
        void DeallocatePointers();
};