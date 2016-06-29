package com.example.puneetchugh.nyctourapp;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by puneetchugh on 6/28/16.
 */
class CustomPagerAdapter extends PagerAdapter {

    private TextView nameView;
    private TextView locationView;
    private TextView timingsView;
    private TextView descriptionView;
    private ImageView imageView;
    private LinkedList<TouristAttractions> touristAttractionsLinkedList;

    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context, LinkedList<TouristAttractions> touristAttractionsLinkedList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.touristAttractionsLinkedList = touristAttractionsLinkedList;


    }

    @Override
    public int getCount() {
        return touristAttractionsLinkedList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item, container, false);

        nameView = (TextView) itemView.findViewById(R.id.name_id);
        locationView = (TextView) itemView.findViewById(R.id.location_id);
        timingsView = (TextView) itemView.findViewById(R.id.timings_id);
        descriptionView = (TextView) itemView.findViewById(R.id.description_id);
        imageView = (ImageView) itemView.findViewById(R.id.imageView_id);


        nameView.setText("Name : " + touristAttractionsLinkedList.get(position).getName());
        locationView.setText("Location : " + touristAttractionsLinkedList.get(position).getLocation());
        timingsView.setText("Timings : " + touristAttractionsLinkedList.get(position).getTimings());
        descriptionView.setText("Description : " + touristAttractionsLinkedList.get(position).getDescription());
        imageView.setImageResource(touristAttractionsLinkedList.get(position).getImage());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}
