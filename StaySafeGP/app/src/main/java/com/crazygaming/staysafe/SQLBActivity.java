/*
 * THIS CLASS IS TO HOLD BASE CLASS FUNCTION OPERATIONS.
 * VIRTUALS AND OTHER THINGS WILL BE STORED HERE.
 */

package com.crazygaming.staysafe;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SQLBActivity extends AppCompatActivity
{
    protected TextView error, results;
    protected EditText etUsername, etPassword;
    protected Button login;
    protected String[] userInfo;

    protected void SaveRecords(String[] resultSet) { }

    protected void CloseForm(String action) { }
}

