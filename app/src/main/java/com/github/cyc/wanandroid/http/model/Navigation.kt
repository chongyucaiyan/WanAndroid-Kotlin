package com.github.cyc.wanandroid.http.model

import com.github.cyc.wanandroid.app.NoProguard

data class Navigation(
        val cid: Int,
        val name: String,
        val articles: List<Article>
) : NoProguard