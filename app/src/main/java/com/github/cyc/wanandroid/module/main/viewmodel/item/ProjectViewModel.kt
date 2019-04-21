package com.github.cyc.wanandroid.module.main.viewmodel.item

import android.databinding.ObservableField
import android.text.TextUtils
import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel
import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.navigator.DetailsNavigator

/**
 * 项目的ViewModel
 */
class ProjectViewModel(private val mDetailsNavigator: DetailsNavigator)
    : BaseItemViewModel<Article>() {

    val imageUrl = ObservableField<String>()

    val title = ObservableField<String>()

    val desc = ObservableField<String>()

    val time = ObservableField<String>()

    val author = ObservableField<String>()

    override fun setAllModel(t: Article) {
        imageUrl.set(t.envelopePic)
        title.set(t.title)
        desc.set(t.desc)
        time.set(t.niceDate)
        author.set(t.author)
    }

    fun onClickItem() {
        val article = mBaseModel
        if (article != null && !TextUtils.isEmpty(article.link)) {
            mDetailsNavigator.startDetailsActivity(article.link)
        }
    }
}