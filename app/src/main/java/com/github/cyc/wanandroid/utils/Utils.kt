package com.github.cyc.wanandroid.utils

/**
 * 工具类
 */
object Utils {

    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return true表示为空，false表示不为空
     */
    fun isListEmpty(list: List<*>?) = list == null || list.isEmpty()
}