package com.tab.fragment.example;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class ListLoader extends AsyncTaskLoader<List<String>> {
    /**
     * @author Rahul Pandey
     * 
     */
    List<String> myList;
    
    public ListLoader(Context context) {
	super(context);
	// TODO Auto-generated constructor stub
	myList=new ArrayList<String>();
	
    }

    @Override
    public List<String> loadInBackground() {
	// TODO Auto-generated method stub
	
	/*
	 * Better test with remote data
	 */
	List<String> list = new ArrayList<String>();
	list.add("EFscs");
	list.add("shasdasd");
	list.add("Adssd");
	list.add("Asdvas");
	list.add("Shhs");
	list.add("Dadasa");
	list.add("EEcs");
	list.add("zxxEEcs");
	
	list.add("Adssd");
	list.add("Asdvas");
	list.add("ydfgdfgEEcs");
	list.add("DDdasa");
	list.add("EEcs");
	list.add("zxxEEcs");
	
	
	List<String> entries = new ArrayList<String>(list.size());
	for (int i = 0; i < list.size(); i++) {
	    entries.add(list.get(i));
	}
	//sorting aaray list
	Collections.sort(entries, ALPHA_COMPARATOR);
	return entries;
    }

    @Override
    public void deliverResult(List<String> data) {
	// TODO Auto-generated method stub
	if (isReset()) {
	    // An async query came in while the loader is stopped. We
	    // don't need the result.
	    if (data != null) {
		onReleaseResources(data);
	    }
	}

	List<String> oldString = data;
	myList = data;
	for (String entry : myList) {
	    Log.i("MYTAG", entry);
	}
	
	if (isStarted()) {
	    // If the Loader is currently started, we can immediately
	    // deliver its results.
	    
	    super.deliverResult(data);
	}
	// At this point we can release the resources associated with
	// 'oldApps' if needed; now that the new result is delivered we
	// know that it is no longer in use.
	if (oldString != null) {
	    onReleaseResources(oldString);
	}

    }

    @Override
    protected void onStartLoading() {
	
	if (myList != null) {
	    // If we currently have a result available, deliver it immediately.
	    deliverResult(myList);
	    forceLoad();
	}
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
	// Attempt to cancel the current load task if possible.
	cancelLoad();
    }

    /**
     * Handles a request to cancel a load.
     */
    @Override
    public void onCanceled(List<String> data) {
	super.onCanceled(data);

	// At this point we can release the resources associated with 'apps'
	// if needed.
	onReleaseResources(data);
    }

    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
	super.onReset();
	// Ensure the loader is stopped
	onStopLoading();
	// At this point we can release the resources associated with 'apps'
	// if needed.
	if (myList != null) {
	    onReleaseResources(myList);
	    myList = null;
	}
    }

    protected void onReleaseResources(List<String> data) {
	// For a simple List<> there is nothing to do. For something
	// like a Cursor, we would close it here.

    }

    public static final Comparator<String> ALPHA_COMPARATOR = new Comparator<String>() {
	private final Collator sCollator = Collator.getInstance(Locale.US);

	@Override
	public int compare(String object1, String object2) {
	    return sCollator.compare(object1, object2);
	}
    };

}
