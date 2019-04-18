package com.github.cyc.wanandroid.module.main.viewholder

import android.view.ViewGroup
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.GlideImageLoader
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder
import com.github.cyc.wanandroid.databinding.ItemBannerBinding
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity
import com.github.cyc.wanandroid.module.main.viewmodel.item.BannerViewModel
import com.github.cyc.wanandroid.navigator.DetailsNavigator
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer

/**
 * Banner的ViewHolder
 */
class BannerViewHolder(parent: ViewGroup)
    : BaseViewHolder<ItemBannerBinding, BannerViewModel>(parent, R.layout.item_banner), DetailsNavigator {

    override fun initViewModel() {
        mViewModel = BannerViewModel(this)
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        mDataBinding.bBanner.run {
            // 设置Banner样式
            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            // 设置图片加载器
            setImageLoader(GlideImageLoader())
            // 设置Banner动画效果
            setBannerAnimation(Transformer.Default)
            // 设置指示器位置（当Banner样式中有指示器时）
            setIndicatorGravity(BannerConfig.RIGHT)
            // 设置Banner监听器
            setOnBannerListener { mViewModel?.onClickBanner(it) }
        }
    }

    override fun startDetailsActivity(url: String) {
        DetailsActivity.start(itemView.context, url)
    }
}