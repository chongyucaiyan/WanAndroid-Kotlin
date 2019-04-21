package com.github.cyc.wanandroid.module.main.adapter

import android.databinding.ObservableList
import android.view.ViewGroup
import com.github.cyc.wanandroid.base.adapter.BaseAdapter
import com.github.cyc.wanandroid.http.model.Navigation
import com.github.cyc.wanandroid.module.main.viewholder.NavigationViewHolder

/**
 * 导航列表Adapter
 */
class NavigationListAdapter(dataList: ObservableList<Any>) : BaseAdapter<NavigationViewHolder>(dataList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NavigationViewHolder(parent)

    override fun onBindViewHolder(holder: NavigationViewHolder, position: Int) {
        holder.getViewModel()?.setBaseModel(mDataList[position] as Navigation)
    }
}