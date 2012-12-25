package com.tab.fragment.example;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class MyAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
   
    private Context mContext;

    public MyAdapter(Context context) {
	super(context, android.R.layout.simple_list_item_2);
	mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	mContext = context;
    }

    public void setData(List<String> data) {
	clear();
	if (data != null) {
	    for (String appEntry : data) {
		
		add(appEntry);
	    }
	}
    }

    /**
     * Populate new items in the list.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

	ViewHolder mHolder;
	if (convertView == null) {
	    convertView = mInflater.inflate(R.layout.list_item_icon_text,parent, false);
	    mHolder = new ViewHolder();
	    mHolder.alphabetHeader = (LinearLayout) convertView.findViewById(R.id.section);
	    mHolder.data = (TextView) convertView.findViewById(R.id.text);
	    convertView.setTag(mHolder);
	} else {
	    mHolder = (ViewHolder) convertView.getTag();
	}
	String metaData = setHeaderAndData(position, mHolder);
	mHolder.data.setText(metaData);
	return convertView;
    }

    private void setSection(LinearLayout header, String label) {
	TextView text = new TextView(mContext);
	header.setBackgroundColor(0xffaabbcc);
	text.setTextColor(Color.WHITE);
	text.setText(label.substring(0, 1).toUpperCase());
	text.setTextSize(20);
	text.setPadding(5, 0, 0, 0);
	text.setGravity(Gravity.CENTER_VERTICAL);
	header.removeAllViews(); // <-- remove previous added
	header.addView(text);
	header.setVisibility(View.VISIBLE);
    }

    private String setHeaderAndData(int position, ViewHolder mHolder) {
	String label = getItem(position);
	char firstChar = label.toUpperCase().charAt(0);// A-Z 0-9 first word
	if (position == 0) {
	    setSection(mHolder.alphabetHeader, label);
	} else {
	    String preLabel = getItem(position - 1);
	    char preFirstChar = preLabel.toUpperCase().charAt(0);
	    if (firstChar != preFirstChar) {
		setSection(mHolder.alphabetHeader, label);
	    } else {
		mHolder.alphabetHeader.setVisibility(View.GONE);
	    }
	}
	return label;
    }

    static class ViewHolder {
	LinearLayout alphabetHeader;
	TextView data;
    }
}
