package com.github.cyc.wanandroid.module.main.adapter

import android.databinding.ObservableList
import android.view.ViewGroup
import com.github.cyc.wanandroid.base.adapter.BaseAdapter
import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.module.main.viewholder.ProjectViewHolder

/**
 * 项目列表Adapter
 */
class ProjectListAdapter(dataList: ObservableList<Any>) : BaseAdapter<ProjectViewHolder>(dataList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProjectViewHolder(parent)

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.getViewModel()?.setBaseModel(mDataList[position] as Article)
    }
}