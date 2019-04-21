package com.github.cyc.wanandroid.module.main.viewmodel

import android.databinding.ObservableArrayList
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.data.DataManager
import com.github.cyc.wanandroid.enums.LoadState
import com.github.cyc.wanandroid.http.base.BaseObserver
import com.github.cyc.wanandroid.http.model.Navigation
import com.github.cyc.wanandroid.utils.RxUtils
import com.github.cyc.wanandroid.utils.Utils

/**
 * 导航tab的ViewModel
 */
class NavigationViewModel(private val mDataManager: DataManager) : BaseViewModel() {

    val dataList = ObservableArrayList<Any>()

    val titleList = ObservableArrayList<String>()

    fun loadData() {
        loadState.set(LoadState.LOADING)
        addDisposable(mDataManager.getNavigationListData()
                .compose(RxUtils.applySchedulers())
                .subscribeWith(object : BaseObserver<List<Navigation>>() {

                    override fun onNextX(t: List<Navigation>) {
                        if (!Utils.isListEmpty(t)) {
                            for (navigation in t) {
                                titleList.add(navigation.name)
                            }
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