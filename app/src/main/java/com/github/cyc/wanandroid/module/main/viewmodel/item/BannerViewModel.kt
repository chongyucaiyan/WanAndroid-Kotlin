package com.github.cyc.wanandroid.module.main.viewmodel.item

import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel
import com.github.cyc.wanandroid.module.main.model.BannerData
import com.github.cyc.wanandroid.navigator.DetailsNavigator

/**
 * Banner的ViewModel
 */
class BannerViewModel(private val mDetailsNavigator: DetailsNavigator) : BaseItemViewModel<BannerData>() {

    override fun setAllModel(t: BannerData) {

    }

    fun onClickBanner(position: Int) {
        val bannerData = mBaseModel
        if (bannerData != null && bannerData.bannerList.size > position) {
            val banner = bannerData.bannerList[position]
            mDetailsNavigator.startDetailsActivity(banner.url)
        }
    }
}