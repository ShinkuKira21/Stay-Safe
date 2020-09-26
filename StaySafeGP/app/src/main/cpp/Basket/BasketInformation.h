/* Author: Edward Patch */

#include "../functions.cpp"

class CBInformation
{
    protected:
        //Strings
        //Name, Category, Allergies, Availability, img
        static std::string name, category,
        allergies, availability, img;

        //Integers
        //Calories
        static int calories;

        //Doubles
        //Price
        static double price;

    public:
        //SetCBInformation Declaration
        static void SetCBInformation();
};