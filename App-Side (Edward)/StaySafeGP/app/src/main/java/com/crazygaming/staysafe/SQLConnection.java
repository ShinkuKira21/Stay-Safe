/* Author: Edward Patch */

package com.crazygaming.staysafe;

import android.os.AsyncTask;

import com.mysql.jdbc.Connection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class SQLConnection
{
    SQLBActivity activity; // Declares the SQLBActivity to be used in the class

    ResultSet resultSet; // Declares the ResultSet to be used in the class and outside of class
    ResultSetMetaData rsMetaData; //Declares the ResultSetMetaData to be used in the class and outside of class

    protected static String server = "82.2.86.25"; // server name
    protected static String prt = "3306"; // server port
    protected static String db = "staysafe"; // database name
    protected static String username = "android"; // server username
    protected static String pwd = "YIHEr2UFUjgemtgn"; // server password

    protected String[] resultColArray; // resultSetArray to store the data
    protected String[][] resultColsArray;

    protected String query; // Query to send a command to Task
    protected String action; // Action to send any actions to enable dynamic queries.
    protected String records = ""; //Stores records
    protected String errors = ""; //Stores errors

    protected String[] userInfo; // User Information

    public SQLConnection(SQLBActivity argSQLBActivity, String argQuery, String argAction, Object argExtraData)
    {
        activity = argSQLBActivity; // Sets activity to argSQLBActivity

        action = argAction; // Sets action to argAction
        query = argQuery; //Sets query to argQuery
        if(action == "Login") userInfo = (String[]) argExtraData; //Sets userInfo to argExtraData
        else resultColsArray = (String[][]) argExtraData;

        new Task(this); // Starts up the Task constructor
        //Will be used to communicate directly to the MYSQL Server.
    }

    public static String GetServer() { return server; }

    //Executed after Task is finished.
    protected void SQLFinalised()
    {
        if(action == "") QueryAction(); // if action is empty string, call QueryAction.
        if(action == "qRows") QuerySave(); // if action is qRows, call QuerySave.
        if(action == "Login") QueryLogin(); // if action is login, call QueryLogin.
    }

    protected void QueryAction()
    {
        try {
            resultColsArray = new String[rsMetaData.getColumnCount()][RowCount()];

            int i = 0;
            while (resultSet.next()) // loop through each row
            {
                //loops through the columns (Columns start from Index 1.)
                for (int j = 1; j <= rsMetaData.getColumnCount(); j++)
                {
                    records += resultSet.getString(j) + "  "; //records each column
                    // j-1 = col, i = row
                    resultColsArray[j-1][i] = resultSet.getString(j); //records each column
                }

                records += "\n"; // records each new line for next row :D

                i++; // adds row to resultColArray
            }
        } catch (Exception e) { errors = e.toString(); }

        //Safety Check
        if(activity.results != null) activity.results.setText(records); // sets Activity's results to records
        if(activity.error != null) activity.error.setText(errors); // sets Activity's errors to records

        activity.SaveRecords(null, resultColsArray); // call activity's save records
    }

    protected void QuerySave()
    {
        //Set size of resultSetArray
        resultColArray = new String[RowCount()];

        try {
            //Set i to 0
            int i = 0;
            while (resultSet.next()) // loop through each row
            {
                //set resultSetArray to column 1 of each row
                resultColArray[i] = resultSet.getString(1);

                i++;
            }
        } catch (Exception e) { errors = e.toString(); }

        activity.SaveRecords(resultColArray, null); // Call SaveRecords function bring over resultSetArray.
    }

    protected void QueryLogin()
    {
        try {
            while(resultSet.next()) // loop through each row
            {
                //If col2 (username) is equal to array[0] (username)
                if(resultSet.getString(2).equals(userInfo[0]))
                {
                    //Uses BCrypt to compare passwords
                    if(BCrypt.checkpw(userInfo[1], resultSet.getString(3)))
                    {
                        errors = "Logged in"; // Error will say logged in
                        activity.CloseForm(resultSet.getString(9), null, null); // Passes through role
                    }

                    //Set password does not exist if username is right
                    else errors = "Password does not exist.";

                    break;
                }
                //Set Errors to Username does not exist if username is wrong
                else errors = "Username does not exist.";
            }
        } catch (Exception e) { errors = e.toString(); }

        //Set Activity's Error text to the errors variable.
        activity.error.setText(errors);
    }

    protected int RowCount()
    {
        // Declare and initialises row size
        int rowSize = 0;

        try {
            resultSet.last(); //Sets resultSet's cursor to last
            rowSize = resultSet.getRow(); // Get's the current row of resultSet's cursor
            resultSet.beforeFirst(); // Sets resultSet's cursor to first row.
        } catch (Exception e) { errors = e.toString(); }

        return rowSize; // returns rowSize
    }
}

// Extends Task to ASyncTask (Gives the class more tools.)
class Task extends AsyncTask<Void, Void, Void>
{
    SQLConnection sql; // Declares SQLConnection as sql.

    public Task(SQLConnection argSql)
    {
        execute(); // Calls the execute command which will make the connection etc...
        sql = argSql; // Sets sql to argSQL.
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Gets the JDBC Driver
            //Sets con to the DriverManager's getConnection function. This will connect the following
            //Argument
            //+ "?useSSL=true&requireSSL=false"
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://" + sql.server + ":" + sql.prt + "/" + sql.db + "?useSSL=true&requireSSL=false", sql.username, sql.pwd);
            //Creates the statement by calling con's createStatement function.
            Statement statement = con.createStatement();

            //If the action is applicable
            if(sql.action == "" || sql.action == "qRows" || sql.action == "Login")
            {
                sql.resultSet = statement.executeQuery(sql.query); // execute query and store the query in sql's resultSet variable
                sql.rsMetaData = sql.resultSet.getMetaData(); // get meta data storing to sql's rsMetaData variable.
            }
            else statement.execute(sql.query); // this is used to execute CREATE or INSERT INTO etc.


        } catch (Exception e) { sql.errors = e.toString(); }

        return null; // return null
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        if(sql.errors != "") sql.activity.error.setText(sql.errors); // set activity's error text to sql.errors if an error has occurred
        else sql.SQLFinalised(); // call sql's SQLFinalised if task is finished and no errors
    }
}
