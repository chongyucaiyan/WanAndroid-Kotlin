package com.github.cyc.wanandroid.module.main.viewholder

import android.view.ViewGroup
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder
import com.github.cyc.wanandroid.databinding.ItemProjectBinding
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity
import com.github.cyc.wanandroid.module.main.viewmodel.item.ProjectViewModel
import com.github.cyc.wanandroid.navigator.DetailsNavigator

/**
 * 项目的ViewHolder
 */
class ProjectViewHolder(parent: ViewGroup)
    : BaseViewHolder<ItemProjectBinding, ProjectViewModel>(parent, R.layout.item_project), DetailsNavigator {

    override fun initViewModel() {
        mViewModel = ProjectViewModel(this)
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {

    }

    override fun startDetailsActivity(url: String) {
        DetailsActivity.start(itemView.context, url)
    }
}