package com.github.cyc.wanandroid.binding

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.cjj.MaterialRefreshLayout
import com.github.cyc.wanandroid.enums.RefreshState
import q.rorbin.verticaltablayout.VerticalTabLayout

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
    fun <T> setDataList(viewPager: ViewPager, dataList: List<T>) {

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
     * 设置VerticalTabLayout的标题
     *
     * @param tabLayout VerticalTabLayout
     * @param titleList 标题列表
     */
    @JvmStatic
    @BindingAdapter("app:titleList")
    fun setTitleList(tabLayout: VerticalTabLayout, titleList: List<String>) {

    }
}