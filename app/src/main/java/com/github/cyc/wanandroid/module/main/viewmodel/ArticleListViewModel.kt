package com.github.cyc.wanandroid.module.main.viewmodel

import android.databinding.ObservableArrayList
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.data.DataManager
import com.github.cyc.wanandroid.enums.LoadState
import com.github.cyc.wanandroid.enums.RefreshState
import com.github.cyc.wanandroid.http.base.BaseObserver
import com.github.cyc.wanandroid.http.model.ArticleList
import com.github.cyc.wanandroid.utils.RxUtils
import com.github.cyc.wanandroid.utils.Utils

/**
 * 文章列表页的ViewModel
 */
class ArticleListViewModel(private val mDataManager: DataManager) : BaseViewModel() {

    val dataList = ObservableArrayList<Any>()

    private var mRefresh = false

    private var mPageNum = 1

    private var mId = -1

    fun setId(id: Int) {
        mId = id
    }

    fun loadData() {
        mRefresh = false
        getAllData()
    }

    override fun reloadData() {
        loadData()
    }

    fun refreshData() {
        mRefresh = true
        getAllData()
    }

    fun loadMoreData() {
        mPageNum++
        getMoreArticleListData()
    }

    private fun getAllData() {
        if (!mRefresh) {
            loadState.set(LoadState.LOADING)
        }

        addDisposable(mDataManager.getWeChatArticleListData(mId, 1)
                .compose(RxUtils.applySchedulers())
                .subscribeWith(object : BaseObserver<ArticleList>(loadState, !mRefresh) {

                    override fun onNextX(t: ArticleList) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END)
                        }

                        val articleList = t.datas
                        if (!Utils.isListEmpty(articleList)) {
                            dataList.clear()
                            dataList.addAll(articleList)

                            mPageNum = 1

                            setHasMore(t.curPage < t.pageCount)

                            if (!mRefresh) {
                                loadState.set(LoadState.SUCCESS)
                            }
                        } else {
                            if (!mRefresh) {
                                loadState.set(LoadState.NO_DATA)
                            }
                        }
                    }

                    override fun onErrorX(errorCode: Int, errorMsg: String) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END)
                        }
                    }
                }))
    }

    private fun getMoreArticleListData() {
        addDisposable(mDataManager.getWeChatArticleListData(mId, mPageNum)
                .compose(RxUtils.applySchedulers())
                .subscribeWith(object : BaseObserver<ArticleList>() {

                    override fun onNextX(t: ArticleList) {
                        setRefreshState(RefreshState.LOAD_MORE_END)

                        val articleList = t.datas
                        if (!Utils.isListEmpty(articleList)) {
                            dataList.addAll(articleList)
                        }

                        if (t.curPage >= t.pageCount) {
                            setHasMore(false)
                        }
                    }

                    override fun onErrorX(errorCode: Int, errorMsg: String) {
                        setRefreshState(RefreshState.LOAD_MORE_END)
                    }
                }))
    }
}