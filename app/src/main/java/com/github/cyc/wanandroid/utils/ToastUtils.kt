package com.github.cyc.wanandroid.utils

import android.support.annotation.StringRes
import android.widget.Toast
import com.github.cyc.wanandroid.app.WanApplication

/**
 * Toast工具类
 */
object ToastUtils {

    /**
     * 显示一条Toast
     *
     * @param msg 消息
     */
    fun show(msg: String) {
        Toast.makeText(WanApplication.instance, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示一条Toast
     *
     * @param resId 消息资源ID
     */
    fun show(@StringRes resId: Int) {
        Toast.makeText(WanApplication.instance, resId, Toast.LENGTH_SHORT).show()
    }
}