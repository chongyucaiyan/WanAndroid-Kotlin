package com.github.cyc.wanandroid.http.model

import com.github.cyc.wanandroid.app.NoProguard

data class Banner(
        val desc: String,
        val id: Int,
        val imagePath: String,
        val isVisible: Int,
        val order: Int,
        val title: String,
        val type: Int,
        val url: String
) : NoProguard