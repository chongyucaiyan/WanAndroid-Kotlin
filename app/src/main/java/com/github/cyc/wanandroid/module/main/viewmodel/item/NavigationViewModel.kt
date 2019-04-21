package com.github.cyc.wanandroid.module.main.viewmodel.item

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel
import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.http.model.Navigation
import com.github.cyc.wanandroid.navigator.DetailsNavigator

/**
 * 导航的ViewModel
 */
class NavigationViewModel(private val mDetailsNavigator: DetailsNavigator)
    : BaseItemViewModel<Navigation>() {

    val name = ObservableField<String>()

    val dataList = ObservableArrayList<Article>()

    override fun setAllModel(t: Navigation) {
        name.set(t.name)
        dataList.addAll(t.articles)
    }

    fun onClickTag(position: Int) {
        if (dataList.size > position) {
            val article = dataList[position]
            mDetailsNavigator.startDetailsActivity(article.link)
        }
    }
}