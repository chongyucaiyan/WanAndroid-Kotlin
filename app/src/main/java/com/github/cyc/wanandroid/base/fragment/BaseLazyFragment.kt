package com.github.cyc.wanandroid.base.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel

/**
 * 懒加载Fragment的基类
 *
 * @param <DB> data binding
 * @param <VM> view model
 */
abstract class BaseLazyFragment<DB : ViewDataBinding, VM : BaseViewModel> : BaseFragment<DB, VM>() {

    private var mVisibleToUser = false

    private var mViewCreated = false

    private var mLazyLoaded = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mVisibleToUser = isVisibleToUser
        lazyLoad()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewCreated = true
        lazyLoad()
    }

    private fun lazyLoad() {
        if (!mLazyLoaded && mVisibleToUser && mViewCreated) {
            mLazyLoaded = true
            onLazyLoad()
        }
    }

    /**
     * 懒加载数据
     */
    abstract fun onLazyLoad()
}