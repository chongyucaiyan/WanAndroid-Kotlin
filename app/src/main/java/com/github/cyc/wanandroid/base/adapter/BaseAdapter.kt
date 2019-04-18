package com.github.cyc.wanandroid.base.adapter

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView

/**
 * Adapter的基类
 */
abstract class BaseAdapter<VH : RecyclerView.ViewHolder>(protected val mDataList: ObservableList<Any>)
    : RecyclerView.Adapter<VH>() {

    init {
        initCallback()
    }

    private fun initCallback() {
        mDataList.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<Any>>() {

            override fun onChanged(sender: ObservableList<Any>) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(sender: ObservableList<Any>, positionStart: Int, itemCount: Int) {
                notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(sender: ObservableList<Any>, positionStart: Int, itemCount: Int) {
                notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeMoved(sender: ObservableList<Any>, fromPosition: Int, toPosition: Int, itemCount: Int) {
                if (itemCount == 1) {
                    notifyItemMoved(fromPosition, toPosition)
                } else {
                    notifyDataSetChanged()
                }
            }

            override fun onItemRangeRemoved(sender: ObservableList<Any>, positionStart: Int, itemCount: Int) {
                notifyItemRangeRemoved(positionStart, itemCount)
            }
        })
    }

    override fun getItemCount() = mDataList.size
}