package com.github.cyc.wanandroid.utils

import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import com.github.cyc.wanandroid.app.WanApplication

/**
 * 资源工具类
 */
object ResourceUtils {

    /**
     * 获取字符串资源
     *
     * @param resId 字符串资源ID
     * @return 字符串
     */
    fun getString(@StringRes resId: Int) = WanApplication.instance.getString(resId)

    /**
     * 获取颜色资源
     *
     * @param resId 颜色资源ID
     * @return 颜色
     */
    fun getColor(@ColorRes resId: Int) = ContextCompat.getColor(WanApplication.instance, resId)
}