package com.crazygaming.staysafe;
import com.crazygaming.staysafe.SQLBActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.mysql.jdbc.Connection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;



public class SQLConnection
{
    SQLBActivity activity;
    Task tsk;

    ResultSet resultSet;
    ResultSetMetaData rsMetaData;

    protected String server = "staysafe-23.mysql.database.azure.com";
    protected String prt = "3306";
    protected String db = "staysafe";
    protected String username = "owner@staysafe-23";
    protected String pwd = "s5hhYSr@LRyD";

    protected String query;
    protected String action;
    protected String records = "";
    protected String errors = "";

    protected String[] userInfo;


    public SQLConnection(SQLBActivity argSQLBActivity, String argQuery, String argAction, String argExtraData[])
    {
        activity = argSQLBActivity;

        action = argAction;
        query = argQuery;
        userInfo = argExtraData;

        tsk = new Task(this);
    }

    protected void SQLFinalised()
    {
        if(action == "") QueryAction();
        if(action == "Login") QueryLogin();
    }

    protected void QueryAction()
    {
        try {
            while (resultSet.next())
            {
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
                    records += resultSet.getString(i) + "  ";

                records += "\n";
            }

        } catch (Exception e) { errors = e.toString(); }

        activity.results.setText(records);
        activity.error.setText(errors);
    }

    protected void QueryLogin()
    {
        try {
            while(resultSet.next())
            {
                if(resultSet.getString(2).equals(userInfo[0]))
                {

                    if(BCrypt.checkpw(userInfo[1], resultSet.getString(3)))
                    {
                        errors = "Logged in";
                        activity.CloseForm();
                    }

                    else errors = "Password does not exist.";

                    break;
                }
                else errors = "Username does not exist.";
            }
        } catch (Exception e) { errors = e.toString(); }

        activity.error.setText(errors);
    }
}

class Task extends AsyncTask<Void, Void, Void>
{
    SQLConnection sql;

    public Task(SQLConnection argSql)
    {
        execute();
        sql = argSql;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://" + sql.server + ":" + sql.prt + "/" + sql.db + "?useSSL=true&requireSSL=false", sql.username, sql.pwd);
            Statement statement = con.createStatement();

            if(sql.action == "" || sql.action == "Login")
            {
                sql.resultSet = statement.executeQuery(sql.query);
                sql.rsMetaData = sql.resultSet.getMetaData();
            }
            else statement.execute(sql.query);


        } catch (Exception e) {
            sql.errors = e.toString();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        if(sql.errors != "") sql.activity.error.setText(sql.errors);
        else sql.SQLFinalised();
    }
}
