package io.martinluo.groceryhelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by martinluo on 2017-05-07.
 */
public class GroceryItemAdapter extends BaseAdapter {

    List<GroceryItem> groceryItems;
    Context context;

    public GroceryItemAdapter(Context context, List<GroceryItem> groceryItems){

        this.context = context;
        this.groceryItems = new LinkedList<>(groceryItems);
    }


    @Override
    public int getCount(){
        return groceryItems.size();
    }

    @Override
    public Object getItem(int position){
        return groceryItems.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textView;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.listlayout, parent, false);
            textView = (TextView) convertView.findViewById(R.id.textBox);

            convertView.setTag(R.id.textBox, textView);
        }
        else{
            textView = (TextView) convertView.getTag(R.id.textBox);
        }

        textView.setText(groceryItems.get(position).getItem());
        textView.setTextColor(Color.parseColor("#bdbdbd"));


        return convertView;
    }


    public void update(GroceryItem tmp) {

        groceryItems.add(tmp);
        notifyDataSetChanged();
    }

    public void update(List<GroceryItem> groceryList){
        groceryItems.clear();
        groceryItems = groceryList;
        notifyDataSetChanged();
    }


}
