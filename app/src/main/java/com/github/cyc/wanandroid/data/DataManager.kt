package com.github.cyc.wanandroid.data

import com.github.cyc.wanandroid.data.source.HttpDataSource
import com.github.cyc.wanandroid.http.model.*
import io.reactivex.Observable

/**
 * 数据管理类
 */
class DataManager private constructor(private val mHttpDataSource: HttpDataSource) : HttpDataSource {

    override fun getBannerData(): Observable<Response<List<Banner>>> =
            mHttpDataSource.getBannerData()

    override fun getTopArticleListData(): Observable<Response<List<Article>>> =
            mHttpDataSource.getTopArticleListData()

    override fun getArticleListData(pageNum: Int): Observable<Response<ArticleList>> =
            mHttpDataSource.getArticleListData(pageNum)

    override fun getSystemListData(): Observable<Response<List<Chapter>>> =
            mHttpDataSource.getSystemListData()

    override fun getWeChatListData(): Observable<Response<List<Chapter>>> =
            mHttpDataSource.getWeChatListData()

    override fun getWeChatArticleListData(id: Int, pageNum: Int): Observable<Response<ArticleList>> =
            mHttpDataSource.getWeChatArticleListData(id, pageNum)

    companion object {

        @Volatile
        private var mInstance: DataManager? = null

        fun getInstance(httpDataSource: HttpDataSource) =
                mInstance ?: synchronized(this) {
                    mInstance ?: DataManager(httpDataSource).apply { mInstance = this }
                }
    }
}