/*
    Author: Edward Patch
 */

package com.crazygaming.staysafe.staffArea;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.crazygaming.staysafe.LoginActivity;
import com.crazygaming.staysafe.R;
import com.crazygaming.staysafe.SQLBActivity;
import com.crazygaming.staysafe.SQLConnection;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class StaffOrdersActivity extends SQLBActivity
{
    SQLConnection sqlConnection;

    protected Button signOut, refresh;
    protected TextView tvStaffName;

    protected String action;
    protected TabLayout tabLayout;
    protected ViewPager vpSliders;

    protected String[][] orderList;
    protected String[] loginDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_orders);

        signOut = findViewById(R.id.btnSignOut);
        refresh = findViewById(R.id.btnRefresh);
        tvStaffName = findViewById(R.id.tvStaffName);

        signOut.setText("Sign Out");
        refresh.setText("Refresh");

        StaffName();
        FetchOrderList();
    }

    @Override
    protected void SaveRecords(String[] resultColArray, String[][] resultColsArray)
    {
        super.SaveRecords(resultColArray, resultColsArray);

        if(action.equals("Orders"))
        {
            //Order List = [Col][Row]
            orderList = new String[resultColsArray.length][resultColsArray[1].length];

            //Sets orderList to resultColsArray.
            for(int i = 0; i < resultColsArray.length; i++)
                for(int j = 0; j < resultColsArray[1].length; j++)
                    orderList[i][j] = resultColsArray[i][j];
        }

        InitialiseSliders(); // Call InitialiseSliders after Query
    }

    protected void InitialiseSliders()
    {
        //Reference: https://www.youtube.com/watch?v=NHBO87ZxGgs, Author:
        // How to Implement Tab Layout With Viewpager in Android Studio
        tabLayout = findViewById(R.id.tabLayout);
        vpSliders = findViewById(R.id.vpSlider);

        //Initialise tab list
        ArrayList<String> tabList = new ArrayList<>();

        //Assign tab list
        tabList.add("Orders");
        tabList.add("Products");

        //Prepare View Pager
        PrepareViewPager(vpSliders, tabList);

        //Setup with View Page
        tabLayout.setupWithViewPager(vpSliders);
    }

    private void PrepareViewPager(ViewPager vpSliders, ArrayList<String> tabList) {
        //Initialise Main Adapter
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        //Initialise Main Fragment
        StaffTabs fragment = new StaffTabs();

        fragment.Set2DArray("Orders", orderList);

        for (int i = 0; i < tabList.size(); i++)
        {
            Bundle bundle = new Bundle(); // creates new bundle

            //Bundles will bring over Parameters to Fragment.
            bundle.putString("action", tabList.get(i));

            //Set Argument
            fragment.setArguments(bundle); // sets the arguments in fragment

            adapter.AddFragment(fragment, tabList.get(i)); // Adds fragment to adapter

            fragment = new StaffTabs(); // Creates a new fragment
        }

        vpSliders.setAdapter(adapter);
    }

    protected void FetchOrderList()
    {
        action = "Orders";
        sqlConnection = new SQLConnection(this, "SELECT * FROM orders", "", null);
    }

    protected void StaffName()
    {
        String staffName = "";

        loginDetails = new String[GetSizes("LDS", 1)];

        for(int i = 0; i < GetSizes("LDS", 0); i++)
            for(int j = 0; j < GetSizes("LDS", 1); j++)
                loginDetails[j] = GetData("LD", i, j);

        if(GetSizes("LDS", 0) == 0)
            staffName += "Error: Press Refresh/Relog.";

        else
            staffName += "Staff Name: " + loginDetails[3] + " " + loginDetails[4];

        tvStaffName.setText(staffName);
    }

    /* Buttons */

    public void SignOut(View view)
    {
        CloseForm("", this, LoginActivity.class);
    }

    public void Refresh(View view)
    {
        refresh.setText("Refreshing...");
        finish();
        startActivity(getIntent());
    }
}