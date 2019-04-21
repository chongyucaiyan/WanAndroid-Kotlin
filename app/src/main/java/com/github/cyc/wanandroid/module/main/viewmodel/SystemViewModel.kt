package com.github.cyc.wanandroid.module.main.viewmodel

import android.databinding.ObservableArrayList
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.data.DataManager
import com.github.cyc.wanandroid.enums.LoadState
import com.github.cyc.wanandroid.enums.RefreshState
import com.github.cyc.wanandroid.http.base.BaseObserver
import com.github.cyc.wanandroid.http.model.Chapter
import com.github.cyc.wanandroid.utils.RxUtils
import com.github.cyc.wanandroid.utils.Utils

/**
 * 体系tab的ViewModel
 */
class SystemViewModel(private val mDataManager: DataManager) : BaseViewModel() {

    val dataList = ObservableArrayList<Any>()

    private var mRefresh = false

    fun loadData() {
        mRefresh = false
        getAllData()
    }

    override fun reloadData() {
        loadData()
    }

    fun refreshData() {
        mRefresh = true
        getAllData()
    }

    private fun getAllData() {
        if (!mRefresh) {
            loadState.set(LoadState.LOADING)
        }

        addDisposable(mDataManager.getSystemListData()
                .compose(RxUtils.applySchedulers())
                .subscribeWith(object : BaseObserver<List<Chapter>>(loadState, !mRefresh) {

                    override fun onNextX(t: List<Chapter>) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END)
                        }

                        if (!Utils.isListEmpty(t)) {
                            dataList.clear()
                            dataList.addAll(t)

                            if (!mRefresh) {
                                loadState.set(LoadState.SUCCESS)
                            }
                        } else {
                            if (!mRefresh) {
                                loadState.set(LoadState.NO_DATA)
                            }
                        }
                    }

                    override fun onErrorX(errorCode: Int, errorMsg: String) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END)
                        }
                    }
                }))
    }
}