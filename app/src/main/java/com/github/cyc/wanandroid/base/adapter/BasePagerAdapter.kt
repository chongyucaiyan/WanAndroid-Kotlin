package com.github.cyc.wanandroid.base.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray

/**
 * PagerAdapter的基类
 */
abstract class BasePagerAdapter<T>(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    protected val mFragmentMap = SparseArray<Fragment>()

    protected var mDataList: List<T>? = null
        private set

    override fun getCount() = mDataList?.size ?: 0

    /**
     * 设置数据列表
     *
     * @param dataList 数据列表
     */
    fun setDataList(dataList: List<T>) {
        mDataList = dataList
        notifyDataSetChanged()
    }

    /**
     * 释放缓存的Fragment
     */
    fun release() {
        mFragmentMap.clear()
    }
}