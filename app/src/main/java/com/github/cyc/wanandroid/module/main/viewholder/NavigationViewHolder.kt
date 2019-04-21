package com.github.cyc.wanandroid.module.main.viewholder

import android.view.ViewGroup
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder
import com.github.cyc.wanandroid.databinding.ItemNavigationBinding
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity
import com.github.cyc.wanandroid.module.main.adapter.NavigationTagAdapter
import com.github.cyc.wanandroid.module.main.viewmodel.item.NavigationViewModel
import com.github.cyc.wanandroid.navigator.DetailsNavigator

/**
 * 导航的ViewHolder
 */
class NavigationViewHolder(parent: ViewGroup)
    : BaseViewHolder<ItemNavigationBinding, NavigationViewModel>(parent, R.layout.item_navigation), DetailsNavigator {

    override fun initViewModel() {
        mViewModel = NavigationViewModel(this)
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        mDataBinding.tflFlowLayout.run {
            adapter = NavigationTagAdapter()
            setOnTagClickListener { view, position, parent ->
                mViewModel?.onClickTag(position)
                true
            }
        }
    }

    override fun startDetailsActivity(url: String) {
        DetailsActivity.start(itemView.context, url)
    }
}