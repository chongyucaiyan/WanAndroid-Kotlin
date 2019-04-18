package com.github.cyc.wanandroid.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * RX工具类
 */
object RxUtils {

    /**
     * IO操作的线程切换
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}