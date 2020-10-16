/*
    Author: Edward Patch
 */


package com.crazygaming.staysafe.staffArea;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FragmentPagerAdapter
{
    //Initialise Tab List
    ArrayList<String> tabList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    public MainAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    public void AddFragment(Fragment fragment, String title)
    {
        //Add Title
        tabList.add(title);

        //Add Fragment
        fragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        //return fragment position
        return fragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        //return fragment size list size
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        //Return Tab List Position
        return tabList.get(position);
    }
}
