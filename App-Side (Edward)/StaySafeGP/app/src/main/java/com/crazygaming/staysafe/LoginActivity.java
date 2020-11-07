/*
    Author: Edward Patch
 */

package com.crazygaming.staysafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.crazygaming.staysafe.staffArea.StaffOrdersActivity;

//Extends SQLBActivity class.
public class LoginActivity extends SQLBActivity
{
    SQLConnection sqlConn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //sets content view to layout activity_login

        login = findViewById(R.id.btnLogin); // sets login to id login
        error = findViewById(R.id.tvError); //sets error to id error

        etUsername = findViewById(R.id.editUsername); // sets etUsername to id editUsername
        etUsername.setHint("Username: "); // sets etUsername hint to Username:

        etPassword = findViewById(R.id.editPassword); // sets etPassword to id editPassword
        etPassword.setHint("Password: "); //sets etPassword hint to Password:

        register = findViewById(R.id.btnRegister); // sets register to id btnRegister
        register.setText("Register"); //sets register text to Register.

        CheckBox chkRemember = findViewById(R.id.chkbxRemember);
        chkRemember.setText("Remember me");

        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setText("Forgot Password?");

        login.setText("Login"); // Sets login text to Login.
        error.setText("No error"); // Sets error to No error.
    }

    public void Login(View view)
    {
        userInfo = new String[2]; //Sets userInfo size to 2
        userInfo[0] = etUsername.getText().toString(); // sets userInfo[0] to etUsername's getText
        userInfo[1] = etPassword.getText().toString(); // sets userInfo[1] to etPassword's getText

        //sets sqlConn to a new SQLConnection passing through, this activity, accounts query, login action and userInfo array.
        sqlConn = new SQLConnection(this, "SELECT * FROM accounts", "Login", userInfo);
    }

    @Override
    public void SaveRecords(String[] resultColArray, String[][] resultColsArray)
    {
        super.SaveRecords(resultColArray, resultColsArray);

        //Record Login Details (RLD)
        ClassSelector("RLD", resultColsArray);
    }

    //Overrides SQLBActivity's CloseForm function
    @Override
    public void CloseForm(String action, SQLBActivity thiz, Class<?> openActivity)
    {
        Intent intentActivity;

        if (action.equals("Student"))
            intentActivity = new Intent(this, ConsumerOrderActivity.class);

        else
            intentActivity = new Intent(this, StaffOrdersActivity.class);

        RecordUsername();

        startActivity(intentActivity);
        finish();
    }

    //Record Username
    protected void RecordUsername()
    {
        String querySearch = "WHERE username = '" + userInfo[0] + "'";

        sqlConn = new SQLConnection(this, "SELECT * FROM accounts " + querySearch, "", null);
    }
}