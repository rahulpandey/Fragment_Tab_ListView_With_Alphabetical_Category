package com.tab.fragment.example;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MyFragClass extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	FragmentManager fm = getSupportFragmentManager();

	// Create the list fragment and add it as our sole content.
	if (fm.findFragmentById(android.R.id.content) == null) {
	    AppListFragment list = new AppListFragment();
	    fm.beginTransaction().add(android.R.id.content, list).commit();
	}
    }

    public static class AppListFragment extends ListFragment implements
	    LoaderManager.LoaderCallbacks<List<String>> {

	// This is the Adapter being used to display the list's data.
	MyAdapter mAdapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);

	    // Give some text to display if there is no data. In a real
	    // application this would come from a resource.
	    setEmptyText(getString(R.string.please_weait));
	    // Create an empty adapter we will use to display the loaded data.
	    mAdapter = new MyAdapter(getActivity());
	    setListAdapter(mAdapter);
	    // Start out with a progress indicator.
	    setListShown(false);
	    
	    getLoaderManager().initLoader(0, null, this);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	    // Insert desired behavior here.
	    Log.i("LoaderCustom", "Item clicked: " + id);
	}

	@Override
	public Loader<List<String>> onCreateLoader(int id, Bundle args) {
	    // This is called when a new Loader needs to be created. This
	    // sample only has one Loader with no arguments, so it is simple.

	    return new ListLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<String>> loader,List<String> data) {
	    // Set the new data in the adapter.

	    mAdapter.setData(data);

	    // The list should now be shown.
	    if (isResumed()) {
		setListShown(true);
	    } else {
		setListShownNoAnimation(true);
	    }
	}

	@Override
	public void onLoaderReset(Loader<List<String>> loader) {
	    // Clear the data in the adapter.
	    mAdapter.setData(null);
	}

    }
}
