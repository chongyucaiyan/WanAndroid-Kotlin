package com.github.cyc.wanandroid.http.model

import com.github.cyc.wanandroid.app.NoProguard

data class Article(
        val apkLink: String,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val envelopePic: String,
        var top: Boolean = false,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val origin: String,
        val projectLink: String,
        val publishTime: Long,
        val superChapterId: Int,
        val superChapterName: String,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int,
        val tags: List<Tag>
) : NoProguard