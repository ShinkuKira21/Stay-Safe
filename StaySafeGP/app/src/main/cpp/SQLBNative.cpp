#include "Handler/Handler.h"

/* C++ Author: Edward Patch
 * This Library will contain offline tasks. This is to improve performance.
 * The C++ library can be accessed from it's based class.
 * */

/* GLOBAL STATIC VARIABLES */

//FUNCTIONS
int Functions::jCols;
int Functions::jRows;

//Handler
std::string** Handler::accountDetails;
int Handler::rowCount;
int Handler::colCount;

//BASKET
std::string** CBInformation::products;
int CBInformation::productCount;
int CBInformation::colCount;

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

    //If selection is equal to ATB
    Handler* handle = new Handler(SQLQuery, sizeResults, selection);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_crazygaming_staysafe_SQLBActivity_GetData(JNIEnv *env, jobject thiz, jstring action, jint i, jint j)
{
    Functions* functions = new Functions();
    std::string selection = functions->JStringConverter(env, action);

    std::string** info;

    //VB - View Basket
    if(selection == "VB")
    {
        info = CBInformation::GetCBInformation();
        return env->NewStringUTF(info[j][i].c_str());
    }

    //LD - Login Details
    if(selection == "LD")
    {
        info = Handler::GetAccountDetails();
        return env->NewStringUTF(info[j][i].c_str());
    }

    return env->NewStringUTF("");
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_crazygaming_staysafe_SQLBActivity_GetSizes(JNIEnv *env, jobject thiz, jstring action, jint i) {
    Functions *functions = new Functions();
    std::string selection = functions->JStringConverter(env, action);

    //Basket Size
    if (selection == "BS") {
        int sizes[2] = {CBInformation::GetProductRows(), CBInformation::GetProductCols()};
        return sizes[i];
    }

    //Login Detail Size
    if (selection == "LDS")
    {
        int sizes[2] = { Handler::GetRowSize(), Handler::GetColSize() };
        return sizes[i];
    }

    return 0;
}
