package com.github.cyc.wanandroid.module.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.adapter.BaseTagAdapter
import com.github.cyc.wanandroid.http.model.Article
import com.zhy.view.flowlayout.FlowLayout

/**
 * 导航TagAdapter
 */
class NavigationTagAdapter : BaseTagAdapter<Article>() {

    override fun getView(parent: FlowLayout, position: Int, t: Article): View {
        val tvTag = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_navigation_tag, parent, false) as TextView
        tvTag.text = t.title

        return tvTag
    }
}