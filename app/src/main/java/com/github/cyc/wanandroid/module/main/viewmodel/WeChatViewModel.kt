package com.github.cyc.wanandroid.module.main.viewmodel

import android.databinding.ObservableArrayList
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.http.model.Chapter

/**
 * 公众号tab的ViewModel
 */
class WeChatViewModel : BaseViewModel() {

    val dataList = ObservableArrayList<Chapter>()
}