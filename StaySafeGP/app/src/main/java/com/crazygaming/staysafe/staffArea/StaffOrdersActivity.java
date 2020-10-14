package com.crazygaming.staysafe.staffArea;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.crazygaming.staysafe.R;
import com.crazygaming.staysafe.SQLBActivity;
import com.crazygaming.staysafe.SQLConnection;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class StaffOrdersActivity extends SQLBActivity
{
    SQLConnection sqlConnection;

    protected String action;
    protected TabLayout tabLayout;
    protected ViewPager vpSliders;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_orders);

        FetchOrderList();
        InitialiseSliders();
    }

    @Override
    protected void SaveRecords(String[] resultColArray, String[][] resultColsArray)
    {
        super.SaveRecords(resultColArray, resultColsArray);

        if(action.equals("Orders"))
        {

        }

    }

    protected void InitialiseSliders()
    {
        //Reference: https://www.youtube.com/watch?v=NHBO87ZxGgs, Author:
        // How to Implement Tablayout With Viewpager in Android Studio
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

        for (int i = 0; i < tabList.size(); i++)
        {
            Bundle bundle = new Bundle(); // creates new bundle
            
            //Logic Goes In Here
            bundle.putString("title", tabList.get(i)); // brings over arguments to fragment

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
}