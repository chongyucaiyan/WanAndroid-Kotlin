package com.github.cyc.wanandroid.http.model

import com.github.cyc.wanandroid.app.NoProguard

data class ArticleList(
        val curPage: Int,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int,
        val datas: List<Article>
) : NoProguard