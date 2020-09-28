/*
 * THIS CLASS IS TO HOLD BASE CLASS FUNCTION OPERATIONS.
 * VIRTUALS AND OTHER THINGS WILL BE STORED HERE.
 */

package com.crazygaming.staysafe;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//Imports AppCompatActivity
public class SQLBActivity extends AppCompatActivity
{
    static
    {
        System.loadLibrary("native-lib");
    }

    //Common Variables
    //Set to null safety checks
    protected TextView error = null, results = null;
    protected EditText etUsername, etPassword;
    protected Button login;
    protected String[] userInfo;

    //SaveRecords will allow record saving
    protected void SaveRecords(String[] resultColArray, String[][] resultColsArray) { }

    //CloseForm will be overridden to close forms by an external class.
    protected void CloseForm(String action, SQLBActivity thiz, Class<?> openActivity)
    {
        Intent consumerOrder = new Intent(thiz, openActivity); // Create new intent
        finish(); // finish this activity
        startActivity(consumerOrder); // Load consumerOrder intent // (Opens LoginActivity)
    }

    // Declares C++ ClassSelector Function (JNI)
    protected native void ClassSelector(String action, String[][] resultColsArray);
    protected native String GetData(String action);
}

