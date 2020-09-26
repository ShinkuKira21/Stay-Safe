//
// Created by edwar on 20/09/2020.
//

#include <jni.h>
#include <string>

/*
 * The following file will contain tools which will allow
 * Java and C++ to work correctly. This file was constructed by
 * Edward Patch.
 */

class Functions
{
    protected:
        const char* cchar;
        std::string converter;

    public:
        /*
         * The following C++ function will convert
         * Java String to C++ Standard String
         */
        std::string JStringConverter(JNIEnv *env, jstring jstr)
        {
            cchar = env->GetStringUTFChars(jstr, 0);

            env->ReleaseStringUTFChars(jstr, cchar);

            converter = cchar;

            return cchar;
        }
};
