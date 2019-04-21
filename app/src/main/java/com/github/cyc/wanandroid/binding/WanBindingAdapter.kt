package com.github.cyc.wanandroid.binding

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.cjj.MaterialRefreshLayout
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.GlideApp
import com.github.cyc.wanandroid.base.adapter.BasePagerAdapter
import com.github.cyc.wanandroid.base.adapter.BaseTagAdapter
import com.github.cyc.wanandroid.enums.RefreshState
import com.github.cyc.wanandroid.module.main.model.BannerData
import com.github.cyc.wanandroid.utils.ResourceUtils
import com.github.cyc.wanandroid.utils.Utils
import com.youth.banner.Banner
import com.zhy.view.flowlayout.TagFlowLayout
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter
import q.rorbin.verticaltablayout.widget.ITabView

/**
 * 应用的BindingAdapter
 */
object WanBindingAdapter {

    /**
     * 设置ViewPager的数据列表
     *
     * @param viewPager ViewPager
     * @param dataList  数据列表
     * @param <T>       数据类型
     */
    @JvmStatic
    @BindingAdapter("app:dataList")
    fun setDataList(viewPager: ViewPager, dataList: List<Nothing>) {
        val adapter = viewPager.adapter
        if (adapter is BasePagerAdapter<*>) {
            adapter.setDataList(dataList)
        }
    }

    /**
     * 设置TagFlowLayout的数据列表
     *
     * @param tagFlowLayout TagFlowLayout
     * @param dataList      数据列表
     * @param <T>           数据类型
     */
    @JvmStatic
    @BindingAdapter("app:dataList")
    fun setDataList(tagFlowLayout: TagFlowLayout, dataList: List<Nothing>) {
        val adapter = tagFlowLayout.adapter
        if (adapter is BaseTagAdapter<*>) {
            adapter.setDataList(dataList)
        }
    }

    /**
     * 设置RefreshLayout的刷新状态
     *
     * @param refreshLayout RefreshLayout
     * @param refreshState  刷新状态
     */
    @JvmStatic
    @BindingAdapter("app:refreshState")
    fun setRefreshState(refreshLayout: MaterialRefreshLayout, refreshState: RefreshState?) {
        refreshState?.run {
            when (refreshState) {
                RefreshState.REFRESH_END -> refreshLayout.finishRefresh()

                RefreshState.LOAD_MORE_END -> {
                    refreshLayout.finishRefreshLoadMore()
                    scrollToNextPosition(refreshLayout)
                }
            }
        }
    }

    private fun scrollToNextPosition(refreshLayout: MaterialRefreshLayout) {
        val child = refreshLayout.getChildAt(0)
        if (child is RecyclerView) {
            val layoutManager = child.layoutManager
            if (layoutManager is LinearLayoutManager) {
                child.smoothScrollToPosition(layoutManager.findLastVisibleItemPosition() + 1)
            }
        }
    }

    /**
     * 设置RefreshLayout的加载更多
     *
     * @param refreshLayout RefreshLayout
     * @param hasMore       true表示还有更多，false表示没有更多了
     */
    @JvmStatic
    @BindingAdapter("app:hasMore")
    fun setHasMore(refreshLayout: MaterialRefreshLayout, hasMore: Boolean?) {
        hasMore?.run { refreshLayout.setLoadMore(hasMore) }
    }

    /**
     * 设置ImageView的图片URL
     *
     * @param imageView ImageView
     * @param imageUrl  图片URL
     */
    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, imageUrl: String?) {
        GlideApp.with(imageView)
                .load(imageUrl)
                .placeholder(R.drawable.ic_timelapse)
                .error(R.drawable.ic_error)
                .into(imageView)
    }

    /**
     * 设置Banner的数据
     *
     * @param banner     Banner
     * @param bannerData Banner数据
     */
    @JvmStatic
    @BindingAdapter("app:bannerData")
    fun setBannerData(banner: Banner, bannerData: BannerData?) {
        if (bannerData == null || Utils.isListEmpty(bannerData.bannerList)) {
            return
        }

        val imageUrlList = mutableListOf<String>()
        val titleList = mutableListOf<String>()

        for (data in bannerData.bannerList) {
            imageUrlList.add(data.imagePath)
            titleList.add(data.title)
        }

        banner.run {
            setImages(imageUrlList)
            setBannerTitles(titleList)
            start()
        }
    }

    /**
     * 设置VerticalTabLayout的标题
     *
     * @param tabLayout VerticalTabLayout
     * @param titleList 标题列表
     */
    @JvmStatic
    @BindingAdapter("app:titleList")
    fun setTitleList(tabLayout: VerticalTabLayout, titleList: List<String>) {
        if (Utils.isListEmpty(titleList)) {
            return
        }

        tabLayout.setTabAdapter(object : SimpleTabAdapter() {

            override fun getCount() = titleList.size

            override fun getTitle(position: Int) =
                    ITabView.TabTitle.Builder()
                            .setContent(titleList[position])
                            .setTextColor(ResourceUtils.getColor(R.color.primary_text),
                                    ResourceUtils.getColor(R.color.secondary_text))
                            .setTextSize(16)
                            .build()
        })
    }
}