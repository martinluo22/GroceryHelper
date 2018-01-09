package io.martinluo.groceryhelper

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.listlayout.view.*

/**
 * Created by martinluo on 2018-01-05.
 */

class GroceryListAdapter(private val context: Context, private val groceryItems: MutableList<GroceryItem>) : RecyclerView.Adapter<GroceryListAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val itemTextView = view.textBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listlayout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = groceryItems[position]
        holder.itemTextView.text = listItem.item
        if (listItem.isRecurring){
            holder.itemTextView.setTextColor(ContextCompat.getColor(context, R.color.grocery_green_dark))
        }
        else{
            holder.itemTextView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDarker))
        }
    }

    override fun getItemCount(): Int {
        return groceryItems.size
    }

    private fun MutableList<GroceryItem>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] // 'this' corresponds to the list
        this[index1] = this[index2]
        this[index2] = tmp
    }
}