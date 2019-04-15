package com.github.cyc.wanandroid.http.model

import com.github.cyc.wanandroid.app.NoProguard
import java.io.Serializable

data class Chapter(
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val isUserControlSetTop: Boolean,
        val visible: Int,
        val children: List<Chapter>
) : NoProguard, Serializable