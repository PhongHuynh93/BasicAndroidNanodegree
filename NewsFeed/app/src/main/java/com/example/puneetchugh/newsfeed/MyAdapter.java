package com.example.puneetchugh.newsfeed;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by puneetchugh on 6/29/16.
 */
public class MyAdapter  extends BaseAdapter
{
    private ArrayList<NewsItem> newsItemArrayList;

    private Context mContext;
    Cursor cursor;
    public MyAdapter(Context context, ArrayList<NewsItem> newsItemArrayList)
    {
        super();
        mContext=context;
        this.newsItemArrayList = newsItemArrayList;

    }

    public int getCount()
    {
        // return the number of records in cursor
        //return cursor.getCount();
        return newsItemArrayList.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position,  View view, ViewGroup parent)
    {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item, null);

        // move the cursor to required position
        //cursor.moveToPosition(position);

        // fetch the sender number and sms body from cursor


        // get the reference of textViews
        TextView textViewTitle=(TextView)view.findViewById(R.id.textView);
        TextView textAuthor=(TextView)view.findViewById(R.id.textView2);

        // Set the Sender number and smsBody to respective TextViews
        textViewTitle.setText(newsItemArrayList.get(position).getWebTitle());
        textAuthor.setText(newsItemArrayList.get(position).getSectionName());


        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}
