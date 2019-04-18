package com.github.cyc.wanandroid.module.main.viewholder

import android.view.View
import android.view.ViewGroup
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder
import com.github.cyc.wanandroid.databinding.ItemArticleBinding
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity
import com.github.cyc.wanandroid.module.main.viewmodel.item.ArticleViewModel
import com.github.cyc.wanandroid.navigator.DetailsNavigator

/**
 * 文章的ViewHolder
 */
class ArticleViewHolder(parent: ViewGroup)
    : BaseViewHolder<ItemArticleBinding, ArticleViewModel>(parent, R.layout.item_article), DetailsNavigator {

    override fun initViewModel() {
        mViewModel = ArticleViewModel(this)
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        mDataBinding.tvTop.visibility = View.GONE
        mDataBinding.tvFresh.visibility = View.GONE
        mDataBinding.tvTag.visibility = View.GONE
    }

    override fun startDetailsActivity(url: String) {
        DetailsActivity.start(itemView.context, url)
    }
}