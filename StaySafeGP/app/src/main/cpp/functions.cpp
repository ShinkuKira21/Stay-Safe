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
        std::string** conversion;

    public:
        Functions() { }
        ~Functions()
        {
            //Cleans cchar from Memory
            delete cchar;

            //Cleans conversion from Memory
            delete[] conversion;
            for (int i = 0; i < sizeof(conversion)/sizeof(conversion[0]); i++)
                delete[] conversion[i];
        }

        /*
         * The following C++ function will convert
         * Java String to C++ Standard String
         */
        std::string JStringConverter(JNIEnv *env, jstring jstr)
        {
            cchar = env->GetStringUTFChars(jstr, 0);

            env->ReleaseStringUTFChars(jstr, cchar);

            env->DeleteLocalRef(jstr);

            return cchar;
        }

        /*
         * The following C++ function will convert
         * JavaObject String Array to C++ 2D Array
         */

        std::string** JObjectArrayConverter(JNIEnv *env, jobjectArray jStrArray)
        {
            // Gets the Rows
            int jRows = env->GetArrayLength(jStrArray);
            // Gets the first row so we can check the columns inside the row
            jobjectArray rowSelection = (jobjectArray) env->GetObjectArrayElement(jStrArray, 0);
            // Gets the columns from the first row (rowSelection)
            int jCol = env->GetArrayLength(rowSelection);

            //Initialises Cols
            conversion = new std::string*[jCol];

            // Used the following link as I was a bit unsure of JNI built in functions
            // Found out (env->GetObjectArrayElement function)
            // https://stackoverflow.com/questions/19591873/get-an-array-of-strings-from-java-to-c-jni

            //Rows
            for(int i = 0; i < jRows; i++)
            {
                //Initialises Rows
                conversion[i] = new std::string[jRows];

                //First row
                rowSelection = (jobjectArray) env->GetObjectArrayElement(jStrArray, i);

                //Cols
                for(int j = 0; j < jCol; j++)
                {
                    jstring currentString = (jstring)env->GetObjectArrayElement(rowSelection, j);
                    conversion[j][i] = env->GetStringUTFChars(currentString, 0);
                }
            }


            return (std::string **) conversion;
        }
};
