package com.github.cyc.wanandroid.module.main.viewmodel.item

import android.databinding.ObservableField
import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel
import com.github.cyc.wanandroid.http.model.Chapter
import com.github.cyc.wanandroid.navigator.SystemDetailsNavigator
import com.github.cyc.wanandroid.utils.Utils

/**
 * 体系的ViewModel
 */
class SystemViewModel(private val mSystemDetailsNavigator: SystemDetailsNavigator)
    : BaseItemViewModel<Chapter>() {

    val parent = ObservableField<String>()

    val children = ObservableField<String>()

    override fun setAllModel(t: Chapter) {
        parent.set(t.name)

        val childList = t.children
        if (!Utils.isListEmpty(childList)) {
            val builder = StringBuilder()
            for (index in childList.indices) {
                if (index > 0) {
                    builder.append("  ")
                }
                builder.append(childList[index].name)
            }

            children.set(builder.toString())
        } else {
            children.set("")
        }
    }

    fun onClickItem() {
        val chapter = mBaseModel
        if (chapter != null) {
            mSystemDetailsNavigator.startSystemDetailsActivity(chapter)
        }
    }
}