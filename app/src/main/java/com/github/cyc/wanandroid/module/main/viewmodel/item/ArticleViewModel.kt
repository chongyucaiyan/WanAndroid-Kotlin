package com.github.cyc.wanandroid.module.main.viewmodel.item

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.text.TextUtils
import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel
import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.navigator.DetailsNavigator
import com.github.cyc.wanandroid.utils.Utils

/**
 * 文章的ViewModel
 */
class ArticleViewModel(private val mDetailsNavigator: DetailsNavigator)
    : BaseItemViewModel<Article>() {

    val tag = ObservableField<String>()

    val author = ObservableField<String>()

    val time = ObservableField<String>()

    val title = ObservableField<String>()

    val chapterName = ObservableField<String>()

    val top = ObservableBoolean()

    val fresh = ObservableBoolean()

    override fun setAllModel(t: Article) {
        author.set(t.author)
        time.set(t.niceDate)
        title.set(t.title)
        chapterName.set("${t.superChapterName} / ${t.chapterName}")
        top.set(t.top)
        fresh.set(t.fresh)

        val tagList = t.tags
        if (!Utils.isListEmpty(tagList)) {
            tag.set(tagList[0].name)
        } else {
            tag.set("")
        }
    }

    fun onClickItem() {
        val article = mBaseModel
        if (article != null && !TextUtils.isEmpty(article.link)) {
            mDetailsNavigator.startDetailsActivity(article.link)
        }
    }
}