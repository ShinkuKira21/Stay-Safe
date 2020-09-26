#include <jni.h>
#include "functions.cpp"

/* C++ Author: Edward Patch
 * This Library will contain offline tasks. This is to improve performance.
 * The C++ library can be accessed from it's based class.
 * */

extern "C"
JNIEXPORT jstring JNICALL
Java_com_crazygaming_staysafe_SQLBActivity_ClassSelector(JNIEnv *env, jobject thiz, jstring action,
                                                         jobjectArray result_cols_array)
{
    Functions* functions = new Functions();

    std::string selection = functions->JStringConverter(env, action);
    std::string** SQLQuery = functions->JObjectArrayConverter(env, result_cols_array);


    //If
    if(selection == "ATB")
    {
        return env->NewStringUTF(("Done " + SQLQuery[0][1]).c_str());
    }

    return env->NewStringUTF("");
}