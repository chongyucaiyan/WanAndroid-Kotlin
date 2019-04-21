package com.github.cyc.wanandroid.navigator

import com.github.cyc.wanandroid.http.model.Chapter

/**
 * 体系详情页导航接口
 */
interface SystemDetailsNavigator {

    /**
     * 打开体系详情页
     *
     * @param chapter Chapter数据
     */
    fun startSystemDetailsActivity(chapter: Chapter)

}