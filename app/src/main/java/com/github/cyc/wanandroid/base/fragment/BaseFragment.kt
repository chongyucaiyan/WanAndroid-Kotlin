package com.github.cyc.wanandroid.base.fragment

import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.databinding.*
import com.github.cyc.wanandroid.enums.LoadState

/**
 * Fragment的基类
 *
 * @param <DB> data binding
 * @param <VM> view model
 */
abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected lateinit var mDataBinding: DB
        private set

    protected var mViewModel: VM? = null

    private lateinit var mFragmentBaseBinding: FragmentBaseBinding

    private var mViewLoadingBinding: ViewLoadingBinding? = null

    private var mViewLoadErrorBinding: ViewLoadErrorBinding? = null

    private var mViewNoNetworkBinding: ViewNoNetworkBinding? = null

    private var mViewNoDataBinding: ViewNoDataBinding? = null

    private var mLoadStateCallback: Observable.OnPropertyChangedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run { handleArguments(this) }

        initViewModel()

        // ViewModel订阅生命周期事件
        mViewModel?.run { lifecycle.addObserver(this) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base,
                container, false)
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutResId(),
                mFragmentBaseBinding.flContentContainer, true)

        bindViewModel()

        if (isSupportLoad()) {
            initLoadState()
        }

        init()

        return mFragmentBaseBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel?.run {
            if (mLoadStateCallback != null) {
                loadState.removeOnPropertyChangedCallback(mLoadStateCallback!!)
            }
        }
    }

    private fun initLoadState() {
        mViewModel?.run {
            mLoadStateCallback = object : Observable.OnPropertyChangedCallback() {

                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    loadState.get()?.run { switchLoadView(this) }
                }
            }
            loadState.addOnPropertyChangedCallback(mLoadStateCallback!!)
        }
    }

    private fun removeLoadView() {
        val childCount = mFragmentBaseBinding.flContentContainer.childCount
        if (childCount > 1) {
            mFragmentBaseBinding.flContentContainer.removeViews(1, childCount - 1)
        }
    }

    private fun switchLoadView(loadState: LoadState) {
        removeLoadView()

        when (loadState) {
            LoadState.LOADING -> {
                if (mViewLoadingBinding == null) {
                    mViewLoadingBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_loading,
                            mFragmentBaseBinding.flContentContainer, false)
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewLoadingBinding?.root)
            }

            LoadState.NO_NETWORK -> {
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_no_network,
                            mFragmentBaseBinding.flContentContainer, false)
                    mViewNoNetworkBinding?.viewModel = mViewModel
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewNoNetworkBinding?.root)
            }

            LoadState.NO_DATA -> {
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_no_data,
                            mFragmentBaseBinding.flContentContainer, false)
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewNoDataBinding?.root)
            }

            LoadState.ERROR -> {
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_load_error,
                            mFragmentBaseBinding.flContentContainer, false)
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewLoadErrorBinding?.root)
            }

            else -> {

            }
        }
    }

    /**
     * 处理参数
     *
     * @param args 参数容器
     */
    protected open fun handleArguments(args: Bundle) {

    }

    /**
     * 是否支持页面加载。默认不支持
     *
     * @return true表示支持，false表示不支持
     */
    protected open fun isSupportLoad() = false

    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract fun getLayoutResId(): Int

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