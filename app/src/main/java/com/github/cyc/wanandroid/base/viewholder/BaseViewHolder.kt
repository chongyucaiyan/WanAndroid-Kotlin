package com.github.cyc.wanandroid.base.viewholder

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel

/**
 * ViewHolder的基类
 *
 * @param <DB> data binding
 * @param <VM> view model
 */
abstract class BaseViewHolder<DB : ViewDataBinding, VM : BaseItemViewModel<*>>(
        parent: ViewGroup,
        @LayoutRes layoutResId: Int
) : RecyclerView.ViewHolder(DataBindingUtil.inflate<DB>(LayoutInflater.from(parent.context),
        layoutResId, parent, false).root) {

    protected var mDataBinding: DB
        private set

    protected var mViewModel: VM? = null

    init {
        mDataBinding = DataBindingUtil.getBinding(itemView)!!

        initViewModel()
        bindViewModel()

        init()
    }

    fun getViewModel() = mViewModel

    /**
     * 初始化ViewModel
     */
    protected abstract fun initViewModel()

    /**
     * 绑定ViewModel
     */
    protected abstract fun bindViewModel()

    /**
     * 初始化
     */
    protected abstract fun init()
}