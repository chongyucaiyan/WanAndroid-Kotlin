package com.github.cyc.wanandroid.module.main.model

import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.http.model.ArticleList
import com.github.cyc.wanandroid.http.model.Banner

data class HomepageData(
        var bannerData: BannerData? = null,
        var topList: List<Article>? = null,
        var articleList: ArticleList? = null
)

data class BannerData(
        val bannerList: List<Banner>
)