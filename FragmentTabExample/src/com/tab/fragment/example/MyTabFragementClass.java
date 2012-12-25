package com.tab.fragment.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyTabFragementClass extends Fragment{
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub
	mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getFragmentManager(), R.id.tabcontent); 
	mTabHost.addTab(mTabHost.newTabSpec("listview").setIndicator("ListView Example"),
        MyFragClass.AppListFragment.class, null);
	
	mTabHost.addTab(mTabHost.newTabSpec("second").setIndicator("Seconds"),
	SecondTabFragement.class, null);
        return mTabHost;
    }
}
