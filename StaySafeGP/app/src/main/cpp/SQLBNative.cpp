#include "functions.cpp"

/* C++ Author: Edward Patch
 * This Library will contain offline tasks. This is to improve performance.
 * The C++ library can be accessed from it's based class.
 * */
extern "C"
JNIEXPORT void JNICALL
Java_com_crazygaming_staysafe_SQLBActivity_ClassSelector(JNIEnv *env, jobject thiz, jstring action,
                                                         jobjectArray resultColsArray)
{
    Functions* functions = new Functions();

    //Converts jstring (action) to std::string (selection)
    std::string selection = functions->JStringConverter(env, action);

    //Converts javaobjectarray (result
    std::string** SQLQuery = functions->JObjectArrayConverter(env, resultColsArray);


    //If selection is equal to ATB
    if(selection == "ATB")
    {

    }
}
