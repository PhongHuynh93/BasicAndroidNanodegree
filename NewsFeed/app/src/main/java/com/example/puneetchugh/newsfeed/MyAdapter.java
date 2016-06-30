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

    public MyAdapter(Context context, ArrayList<NewsItem> newsItemArrayList)
    {
        super();
        mContext=context;
        this.newsItemArrayList = newsItemArrayList;
    }

    public int getCount()
    {
        return newsItemArrayList.size();
    }

    
    public View getView(int position,  View view, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item, null);

        TextView textViewTitle=(TextView)view.findViewById(R.id.textView);
        TextView textAuthor=(TextView)view.findViewById(R.id.textView2);

        textViewTitle.setText(newsItemArrayList.get(position).getWebTitle());
        textAuthor.setText(newsItemArrayList.get(position).getSectionName());
        return view;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
}
