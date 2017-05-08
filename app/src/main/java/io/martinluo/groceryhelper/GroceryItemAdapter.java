package io.martinluo.groceryhelper;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by martinluo on 2017-05-07.
 */
public class GroceryItemAdapter extends BaseAdapter {

    List<GroceryItem> groceryItems;


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

    /*
    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
    }
    */


    public void addItems(GroceryItem tmp){
        groceryItems.add(tmp);
        notifyDataSetChanged();
    }


}
