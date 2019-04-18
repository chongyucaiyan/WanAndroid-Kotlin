package com.github.cyc.wanandroid.http.base

/**
 * 自定义的Observer
 */
interface ObserverX<T> {

    /**
     * 成功时回调
     *
     * @param t 具体的网络请求响应model
     */
    fun onNextX(t: T)

    /**
     * 出错时回调。与onCompleteX()方法互斥
     *
     * @param errorCode 错误码
     * @param errorMsg  错误信息
     */
    fun onErrorX(errorCode: Int, errorMsg: String)

    /**
     * 结束时回调。与onErrorX()方法互斥
     */
    fun onCompleteX()
}