package com.github.cyc.wanandroid.base.adapter

import com.zhy.view.flowlayout.TagAdapter

/**
 * TagAdapter的基类
 *
 * @param <T> 数据类型
 */
abstract class BaseTagAdapter<T> : TagAdapter<T>(listOf()) {

    private var mDataList: List<T>? = null

    override fun getCount() = mDataList?.size ?: 0

    override fun getItem(position: Int) = mDataList!![position]

    /**
     * 设置数据列表
     *
     * @param dataList 数据列表
     */
    fun setDataList(dataList: List<T>) {
        mDataList = dataList
        notifyDataChanged()
    }
}