package com.github.cyc.wanandroid.app

import com.github.cyc.wanandroid.data.DataManager
import com.github.cyc.wanandroid.data.source.HttpDataSourceImpl
import com.github.cyc.wanandroid.http.RetrofitManager
import com.github.cyc.wanandroid.http.api.ApiService

/**
 * 依赖注入
 */
object Injection {

    /**
     * 提供DataManager
     *
     * @return DataManager
     */
    fun provideDataManager() =
            DataManager.getInstance(HttpDataSourceImpl.getInstance(
                    RetrofitManager.get().create(ApiService::class.java)))
}