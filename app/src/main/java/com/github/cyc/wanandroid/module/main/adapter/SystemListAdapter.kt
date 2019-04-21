package com.github.cyc.wanandroid.module.main.adapter

import android.databinding.ObservableList
import android.view.ViewGroup
import com.github.cyc.wanandroid.base.adapter.BaseAdapter
import com.github.cyc.wanandroid.http.model.Chapter
import com.github.cyc.wanandroid.module.main.viewholder.SystemViewHolder

/**
 * 体系列表Adapter
 */
class SystemListAdapter(dataList: ObservableList<Any>) : BaseAdapter<SystemViewHolder>(dataList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SystemViewHolder(parent)

    override fun onBindViewHolder(holder: SystemViewHolder, position: Int) {
        holder.getViewModel()?.setBaseModel(mDataList[position] as Chapter)
    }
}