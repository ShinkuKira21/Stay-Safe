#include "Basket/Basket.h"

/* C++ Author: Edward Patch
 * This Library will contain offline tasks. This is to improve performance.
 * The C++ library can be accessed from it's based class.
 * */

/* GLOBAL STATIC VARIABLES */

//FUNCTIONS
int Functions::jCols;
int Functions::jRows;

//BASKET
std::string** CBInformation::products;
int CBInformation::productCount;

static int dataSize[2];

extern "C"
JNIEXPORT void JNICALL
Java_com_crazygaming_staysafe_SQLBActivity_ClassSelector(JNIEnv *env, jobject thiz, jstring action,
                                                         jobjectArray resultColsArray)
{
    Functions* functions = new Functions();

    //Converts jstring (action) to std::string (selection)
    std::string selection = functions->JStringConverter(env, action);

    //Converts javaobjectarray (result)
    std::string** SQLQuery = functions->JObjectArrayConverter(env, resultColsArray);

    int sizeResults[2] = {Functions::GetDataRow(), Functions::GetDataCol()};

    for(int i = 0; i < 2; i++)
        dataSize[i] = sizeResults[i];

    //If selection is equal to ATB
    if(selection == "ATB" || selection == "RFB")
        Basket* basket = new Basket(SQLQuery, sizeResults, selection);

}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_crazygaming_staysafe_SQLBActivity_GetData(JNIEnv *env, jobject thiz, jstring action, jint i, jint j)
{
    Functions* functions = new Functions();
    std::string selection = functions->JStringConverter(env, action);

    std::string** info = CBInformation::GetCBInformation();

    //VB - View Basket
    if(selection == "VB") return env->NewStringUTF(info[j][i].c_str());

    return env->NewStringUTF("");
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_crazygaming_staysafe_SQLBActivity_GetSizes(JNIEnv *env, jobject thiz, jint i)
{
    //0 = Row, 1 = Column
    int sizes[2] = {CBInformation::GetProductRows(), Functions::GetDataCol()};

    return sizes[i];
}
