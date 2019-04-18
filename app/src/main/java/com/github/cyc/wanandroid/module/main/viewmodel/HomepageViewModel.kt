package com.github.cyc.wanandroid.module.main.viewmodel

import android.databinding.ObservableArrayList
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.data.DataManager
import com.github.cyc.wanandroid.enums.LoadState
import com.github.cyc.wanandroid.enums.RefreshState
import com.github.cyc.wanandroid.http.HttpCode
import com.github.cyc.wanandroid.http.base.BaseObserver
import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.http.model.ArticleList
import com.github.cyc.wanandroid.http.model.Banner
import com.github.cyc.wanandroid.http.model.Response
import com.github.cyc.wanandroid.module.main.model.BannerData
import com.github.cyc.wanandroid.module.main.model.HomepageData
import com.github.cyc.wanandroid.utils.RxUtils
import com.github.cyc.wanandroid.utils.Utils
import io.reactivex.Observable
import io.reactivex.functions.Function3

/**
 * 首页tab的ViewModel
 */
class HomepageViewModel(private val mDataManager: DataManager) : BaseViewModel() {

    val dataList = ObservableArrayList<Any>()

    private var mRefresh = false

    private var mPageNum = 0

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

        val bannerObservable = mDataManager.getBannerData()
        val topListObservable = mDataManager.getTopArticleListData()
        val articleListObservable = mDataManager.getArticleListData(0)

        addDisposable(Observable.zip(bannerObservable, topListObservable, articleListObservable,
                Function3<Response<List<Banner>>, Response<List<Article>>, Response<ArticleList>, Response<HomepageData>> { bannerResponse, topListResponse, articleListResponse ->
                    val response = Response(HttpCode.SUCCESS, "", HomepageData())

                    if (articleListResponse.errorCode != HttpCode.SUCCESS) {
                        response.errorCode = articleListResponse.errorCode
                        response.errorMsg = articleListResponse.errorMsg
                        return@Function3 response
                    }

                    if (bannerResponse.errorCode == HttpCode.SUCCESS) {
                        response.data.bannerData = BannerData(bannerResponse.data)
                    }

                    if (topListResponse.errorCode == HttpCode.SUCCESS) {
                        val topList = topListResponse.data
                        if (!Utils.isListEmpty(topList)) {
                            for (article in topList) {
                                article.top = true
                            }
                        }
                        response.data.topList = topList
                    }

                    response.data.articleList = articleListResponse.data

                    response
                })
                .compose(RxUtils.applySchedulers())
                .subscribeWith(object : BaseObserver<HomepageData>(loadState, !mRefresh) {

                    override fun onNextX(t: HomepageData) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END)
                        }

                        val bannerData = t.bannerData
                        val topList = t.topList
                        val articleList = t.articleList

                        if (articleList != null && !Utils.isListEmpty(articleList.datas)) {
                            dataList.clear()
                            if (!Utils.isListEmpty(bannerData?.bannerList)) {
                                dataList.add(bannerData)
                            }
                            if (!Utils.isListEmpty(topList)) {
                                dataList.addAll(topList!!)
                            }
                            dataList.addAll(articleList.datas)

                            mPageNum = 0

                            if (articleList.curPage >= articleList.pageCount) {
                                setHasMore(false)
                            } else {
                                setHasMore(true)
                            }

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
        addDisposable(mDataManager.getArticleListData(mPageNum)
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