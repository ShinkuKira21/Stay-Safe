#include <jni.h>
#include "functions.cpp"

/* C++ Author: Edward Patch
 * This Library will contain offline tasks. This is to improve performance.
 * The C++ library can be accessed from it's based class.
 * */

extern "C"
JNIEXPORT void JNICALL
Java_com_crazygaming_staysafe_SQLBActivity_ClassSelector(JNIEnv *env, jobject thiz,
                                                         jstring cls)
{
    Functions functions;

    std::string selection = functions.JStringConverter(env, cls) + " ";


    //If
    if(selection == "Basket")
    {

    }
}