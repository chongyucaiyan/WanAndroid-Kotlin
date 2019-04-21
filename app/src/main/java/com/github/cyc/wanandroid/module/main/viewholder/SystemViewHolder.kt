package com.github.cyc.wanandroid.module.main.viewholder

import android.view.ViewGroup
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder
import com.github.cyc.wanandroid.databinding.ItemSystemBinding
import com.github.cyc.wanandroid.http.model.Chapter
import com.github.cyc.wanandroid.module.main.activity.SystemDetailsActivity
import com.github.cyc.wanandroid.module.main.viewmodel.item.SystemViewModel
import com.github.cyc.wanandroid.navigator.SystemDetailsNavigator

/**
 * 体系的ViewHolder
 */
class SystemViewHolder(parent: ViewGroup)
    : BaseViewHolder<ItemSystemBinding, SystemViewModel>(parent, R.layout.item_system), SystemDetailsNavigator {

    override fun initViewModel() {
        mViewModel = SystemViewModel(this)
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {

    }

    override fun startSystemDetailsActivity(chapter: Chapter) {
        SystemDetailsActivity.start(itemView.context, chapter)
    }
}