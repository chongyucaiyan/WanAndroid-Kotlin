package com.github.cyc.wanandroid.module.main.viewmodel

import android.databinding.ObservableArrayList
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel

/**
 * 导航tab的ViewModel
 */
class NavigationViewModel : BaseViewModel() {

    val titleList = ObservableArrayList<String>()
}