package com.github.cyc.wanandroid.http.model

import com.github.cyc.wanandroid.app.NoProguard

/**
 * 网络请求响应model
 *
 * @param <T> 具体的响应model
 */
data class Response<T>(
        var errorCode: Int,
        var errorMsg: String,
        val data: T
) : NoProguard