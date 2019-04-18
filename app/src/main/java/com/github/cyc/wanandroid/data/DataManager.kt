package com.github.cyc.wanandroid.data

import com.github.cyc.wanandroid.data.source.HttpDataSource
import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.http.model.ArticleList
import com.github.cyc.wanandroid.http.model.Banner
import com.github.cyc.wanandroid.http.model.Response
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

    companion object {

        @Volatile
        private var mInstance: DataManager? = null

        fun getInstance(httpDataSource: HttpDataSource) =
                mInstance ?: synchronized(this) {
                    mInstance ?: DataManager(httpDataSource).apply { mInstance = this }
                }
    }
}