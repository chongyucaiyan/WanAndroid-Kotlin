package com.github.cyc.wanandroid.module.main.adapter

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.cyc.wanandroid.base.adapter.BaseAdapter
import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.module.main.model.BannerData
import com.github.cyc.wanandroid.module.main.viewholder.ArticleViewHolder
import com.github.cyc.wanandroid.module.main.viewholder.BannerViewHolder

/**
 * 文章列表Adapter
 */
class ArticleListAdapter(dataList: ObservableList<Any>) : BaseAdapter<RecyclerView.ViewHolder>(dataList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                VIEW_TYPE_ARTICLE -> ArticleViewHolder(parent)

                VIEW_TYPE_BANNER -> BannerViewHolder(parent)

                else -> ArticleViewHolder(parent)
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mDataList[position]
        when (data) {
            is Article -> (holder as ArticleViewHolder).getViewModel()?.setBaseModel(data)

            is BannerData -> (holder as BannerViewHolder).getViewModel()?.setBaseModel(data)

            else -> {
            }
        }
    }

    override fun getItemViewType(position: Int) =
            when (mDataList[position]) {
                is Article -> VIEW_TYPE_ARTICLE

                is BannerData -> VIEW_TYPE_BANNER

                else -> super.getItemViewType(position)
            }

    companion object {

        private const val VIEW_TYPE_ARTICLE = 1
        private const val VIEW_TYPE_BANNER = 2
    }
}