package io.martinluo.groceryhelper;

import java.io.Serializable;

/**
 * Created by martinluo on 2017-05-07.
 */
public class GroceryItem implements Serializable {

    private String item;

    private boolean isRecurring;

    public GroceryItem(String item, boolean isRecurring){
        this.item = item;
        this.isRecurring = isRecurring;
    }

    public String getItem() {
        return item;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }
}
