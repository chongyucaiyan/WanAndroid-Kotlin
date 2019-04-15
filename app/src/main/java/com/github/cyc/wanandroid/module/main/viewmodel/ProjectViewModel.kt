package com.github.cyc.wanandroid.module.main.viewmodel

import android.databinding.ObservableArrayList
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.http.model.Chapter

/**
 * 项目tab的ViewModel
 */
class ProjectViewModel : BaseViewModel() {

    val dataList = ObservableArrayList<Chapter>()
}