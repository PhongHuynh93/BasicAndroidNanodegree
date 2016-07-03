package com.example.puneetchugh.invertorymanagement;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by puneetchugh on 7/2/16.
 */
public class InventoryListFragment extends Fragment {

    private ListView listView;
    private ArrayList<InventoryItem> inventoryItemArrayList;
    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase db;
    static InventoryListFragment fragment;

    public static InventoryListFragment newInstance(int page) {

        fragment = new InventoryListFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventorylist_fragment, container, false);
        TextView textWhenNoListId = (TextView) view.findViewById(R.id.when_no_list);
        mySQLiteHelper = new MySQLiteHelper(getActivity());
        inventoryItemArrayList = new ArrayList<>();
        inventoryItemArrayList = mySQLiteHelper.getListOfInventoryItem();
        mySQLiteHelper.close();

        if(inventoryItemArrayList.size() == 0){

        }else {
            textWhenNoListId.setVisibility(View.GONE);
            listView = (ListView)view.findViewById(R.id.list_view);
            InventoryCustomAdapter inventoryCustomAdapter = new InventoryCustomAdapter(inventoryItemArrayList, getActivity());
            listView.setAdapter(inventoryCustomAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (inventoryItemArrayList.size() == 0) {

                    } else {
                        Intent intent = new Intent(getActivity(), ProductDetails.class);
                        intent.putExtra("inventory_item", inventoryItemArrayList.get(position));
                        //getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        startActivity(intent);
                    }
                }
            });
        }
        return view;
    }
}
