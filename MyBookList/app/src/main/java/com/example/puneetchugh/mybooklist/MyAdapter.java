package com.example.puneetchugh.mybooklist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by puneetchugh on 6/30/16.
 */
public class MyAdapter  extends BaseAdapter
{
    private ArrayList<BookItem> bookItemArrayList;
    private Context mContext;

    public MyAdapter(Context context, ArrayList<BookItem> bookItemArrayList)
    {
        super();
        mContext=context;
        this.bookItemArrayList = bookItemArrayList;

    }

    public int getCount()
    {
        return bookItemArrayList.size();
    }

    public View getView(int position,  View view, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item, null);
        TextView textViewTitle=(TextView)view.findViewById(R.id.textView);
        TextView textAuthor=(TextView)view.findViewById(R.id.textView2);
        textViewTitle.setText(bookItemArrayList.get(position).getTitle());
        textAuthor.setText(bookItemArrayList.get(position).getAuthor());

        return view;
    }

    public Object getItem(int position) {
        return bookItemArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
}
