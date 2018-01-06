package io.martinluo.groceryhelper;

import java.io.Serializable;

/**
 * Created by martinluo on 2017-05-07.
 */
public class GroceryItem implements Serializable {

    private String mItem;

    private boolean isRecurring;

    public GroceryItem(String item, boolean isRecurring){
        this.mItem = item;
        this.isRecurring = isRecurring;
    }

    public String getItem() {
        return mItem;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }
}
