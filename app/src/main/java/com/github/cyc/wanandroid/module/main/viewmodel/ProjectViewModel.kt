package com.github.cyc.wanandroid.module.main.viewmodel

import android.databinding.ObservableArrayList
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.data.DataManager
import com.github.cyc.wanandroid.enums.LoadState
import com.github.cyc.wanandroid.http.base.BaseObserver
import com.github.cyc.wanandroid.http.model.Chapter
import com.github.cyc.wanandroid.utils.RxUtils
import com.github.cyc.wanandroid.utils.Utils

/**
 * 项目tab的ViewModel
 */
class ProjectViewModel(private val mDataManager: DataManager) : BaseViewModel() {

    val dataList = ObservableArrayList<Chapter>()

    fun loadData() {
        loadState.set(LoadState.LOADING)
        addDisposable(mDataManager.getProjectListData()
                .compose(RxUtils.applySchedulers())
                .subscribeWith(object : BaseObserver<List<Chapter>>(loadState) {

                    override fun onNextX(t: List<Chapter>) {
                        if (!Utils.isListEmpty(t)) {
                            dataList.addAll(t)
                            loadState.set(LoadState.SUCCESS)
                        } else {
                            loadState.set(LoadState.NO_DATA)
                        }
                    }
                }))
    }

    override fun reloadData() {
        loadData()
    }
}