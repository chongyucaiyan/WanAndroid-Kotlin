package com.github.cyc.wanandroid.base.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.github.cyc.wanandroid.BR

/**
 * Item的ViewModel的基类
 *
 * @param <T> 基础model的类型
 */
abstract class BaseItemViewModel<T> : BaseObservable() {

    protected var mBaseModel: T? = null
        private set

    /**
     * 获取基础model
     *
     * @return 基础model
     */
    @Bindable
    fun getBaseModel() = mBaseModel

    /**
     * 设置基础model
     *
     * @param t 基础model
     */
    fun setBaseModel(t: T) {
        mBaseModel = t
        notifyPropertyChanged(BR.baseModel)
        setAllModel(t)
    }

    /**
     * 设置所有的model。如果设置了基础model，那么会设置所有的model
     *
     * @param t 基础model
     */
    protected abstract fun setAllModel(t: T)
}