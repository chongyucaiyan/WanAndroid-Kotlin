package com.github.cyc.wanandroid.base.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.databinding.*
import com.github.cyc.wanandroid.enums.LoadState

/**
 * Activity的基类
 *
 * @param <DB> data binding
 * @param <VM> view model
 */
abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var mDataBinding: DB
        private set

    protected var mViewModel: VM? = null

    private lateinit var mActivityBaseBinding: ActivityBaseBinding

    private var mViewLoadingBinding: ViewLoadingBinding? = null

    private var mViewLoadErrorBinding: ViewLoadErrorBinding? = null

    private var mViewNoNetworkBinding: ViewNoNetworkBinding? = null

    private var mViewNoDataBinding: ViewNoDataBinding? = null

    private var mLoadStateCallback: Observable.OnPropertyChangedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)

        mActivityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        mDataBinding = DataBindingUtil.inflate(layoutInflater, getLayoutResId(),
                mActivityBaseBinding.flContentContainer, true)

        initViewModel()
        bindViewModel()

        if (isSupportLoad()) {
            initLoadState()
        }

        init()

        // ViewModel订阅生命周期事件
        mViewModel?.run { lifecycle.addObserver(this) }
    }

    override fun onDestroy() {
        super.onDestroy()
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
        val childCount = mActivityBaseBinding.flContentContainer.childCount
        if (childCount > 1) {
            mActivityBaseBinding.flContentContainer.removeViews(1, childCount - 1)
        }
    }

    private fun switchLoadView(loadState: LoadState) {
        removeLoadView()

        when (loadState) {
            LoadState.LOADING -> {
                if (mViewLoadingBinding == null) {
                    mViewLoadingBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_loading,
                            mActivityBaseBinding.flContentContainer, false)
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadingBinding?.root)
            }

            LoadState.NO_NETWORK -> {
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_no_network,
                            mActivityBaseBinding.flContentContainer, false)
                    mViewNoNetworkBinding?.viewModel = mViewModel
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoNetworkBinding?.root)
            }

            LoadState.NO_DATA -> {
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_no_data,
                            mActivityBaseBinding.flContentContainer, false)
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoDataBinding?.root)
            }

            LoadState.ERROR -> {
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_load_error,
                            mActivityBaseBinding.flContentContainer, false)
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadErrorBinding?.root)
            }

            else -> {

            }
        }
    }

    /**
     * 处理参数
     *
     * @param intent 参数容器
     */
    protected open fun handleIntent(intent: Intent) {

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