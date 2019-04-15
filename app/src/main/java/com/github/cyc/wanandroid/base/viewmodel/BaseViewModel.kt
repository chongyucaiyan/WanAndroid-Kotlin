package com.github.cyc.wanandroid.base.viewmodel

import android.arch.lifecycle.DefaultLifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField
import com.android.databinding.library.baseAdapters.BR
import com.github.cyc.wanandroid.enums.LoadState
import com.github.cyc.wanandroid.enums.RefreshState
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * ViewModel的基类
 */
abstract class BaseViewModel : BaseObservable(), DefaultLifecycleObserver {

    val loadState = ObservableField<LoadState>()

    // 因为设置相同的值也要通知改变，所以采用@Bindable的方式
    private var refreshState: RefreshState? = null

    private var hasMore: Boolean? = null

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(owner: LifecycleOwner) {
        Logger.i(this.toString())
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Logger.i(this.toString())

        mCompositeDisposable?.run { clear() }
    }

    /**
     * 获取RefreshLayout的刷新状态
     *
     * @return 刷新状态
     */
    @Bindable
    fun getRefreshState() = refreshState

    /**
     * 设置RefreshLayout的刷新状态
     *
     * @param refreshState 刷新状态
     */
    protected fun setRefreshState(refreshState: RefreshState) {
        this.refreshState = refreshState
        notifyPropertyChanged(BR.refreshState)
    }

    /**
     * 获取RefreshLayout的加载更多
     *
     * @return true表示还有更多，false表示没有更多了
     */
    @Bindable
    fun getHasMore() = hasMore

    /**
     * 设置RefreshLayout的加载更多
     *
     * @param hasMore true表示还有更多，false表示没有更多了
     */
    protected fun setHasMore(hasMore: Boolean) {
        this.hasMore = hasMore
        notifyPropertyChanged(BR.hasMore)
    }

    /**
     * 重新加载数据。没有网络，点击重试时回调
     */
    open fun reloadData() {

    }

    /**
     * 添加订阅事件
     *
     * @param disposable 订阅事件
     */
    protected fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }
}