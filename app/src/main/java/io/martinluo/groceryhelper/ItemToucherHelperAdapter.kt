package io.martinluo.groceryhelper

/**
 * Created by martinluo on 2018-01-06.
 */
interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)
}