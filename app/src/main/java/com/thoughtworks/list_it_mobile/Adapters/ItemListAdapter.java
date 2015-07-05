package com.thoughtworks.list_it_mobile.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thoughtworks.list_it_mobile.Item;
import com.thoughtworks.list_it_mobile.R;

import java.util.List;

public class ItemListAdapter extends ArrayAdapter<Item> {


    public ItemListAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_layout, null);

        Item item = getItem(position);

        TextView nameTextView = (TextView) view.findViewById(R.id.list_item_name);
        nameTextView.setText(item.name);

        TextView descriptionTextView = (TextView) view.findViewById(R.id.description);
        descriptionTextView.setText(item.description);


        return view;
    }

}
