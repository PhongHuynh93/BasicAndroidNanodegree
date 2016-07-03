package com.example.puneetchugh.invertorymanagement;

import android.app.ListFragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    public static InventoryListFragment newInstance(int page) {

        InventoryListFragment fragment = new InventoryListFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySQLiteHelper = new MySQLiteHelper(getActivity());
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventorylist_fragment, container, false);
        listView = (ListView)view.findViewById(R.id.list_view);

        inventoryItemArrayList = new ArrayList<>();
        inventoryItemArrayList = mySQLiteHelper.getListOfInventoryItem();
        InventoryCustomAdapter inventoryCustomAdapter = new InventoryCustomAdapter(inventoryItemArrayList,getActivity());
        listView.setAdapter(inventoryCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent= new Intent(getActivity(),ProductDetails.class);
                intent.putExtra("inventory_item",inventoryItemArrayList.get(position));
                startActivity(intent);
            }
        });
        return view;
    }
}
