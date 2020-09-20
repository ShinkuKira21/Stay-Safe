package com.crazygaming.staysafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crazygaming.staysafe.SQLBActivity;

public class LoginActivity extends SQLBActivity
{
    SQLConnection sqlConn;

    // Used to load the 'native-lib' library on application startup.
    static
    {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Example of a call to a native method
        login = findViewById(R.id.login);
        error = findViewById(R.id.error);

        TextView tvUsername = findViewById(R.id.tvUsername);
        etUsername = findViewById(R.id.editUsername);
        tvUsername.setText("Username: ");


        TextView tvPassword = findViewById(R.id.tvPassword);
        etPassword = findViewById(R.id.editPassword);
        tvPassword.setText("Password: ");

        login.setText("Login");
        error.setText("No error");
    }

    public void Login(View view) {
        userInfo = new String[2];
        userInfo[0] = etUsername.getText().toString();
        userInfo[1] = etPassword.getText().toString();

        //sqlConn = new SQLConnection(results, error, "SELECT * FROM tblStaff", "", null);
        sqlConn = new SQLConnection(this, "SELECT * FROM accounts", "Login", userInfo);
    }

    @Override
    public void CloseForm()
    {
        finish();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
