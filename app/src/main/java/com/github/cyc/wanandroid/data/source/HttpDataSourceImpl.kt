package com.github.cyc.wanandroid.data.source

import com.github.cyc.wanandroid.http.api.ApiService
import com.github.cyc.wanandroid.http.model.*
import io.reactivex.Observable

/**
 * 网络数据源的实现
 */
class HttpDataSourceImpl private constructor(private val mApiService: ApiService) : HttpDataSource {

    override fun getBannerData(): Observable<Response<List<Banner>>> =
            mApiService.getBannerData()

    override fun getTopArticleListData(): Observable<Response<List<Article>>> =
            mApiService.getTopArticleListData()

    override fun getArticleListData(pageNum: Int): Observable<Response<ArticleList>> =
            mApiService.getArticleListData(pageNum)

    override fun getSystemListData(): Observable<Response<List<Chapter>>> =
            mApiService.getSystemListData()

    override fun getWeChatListData(): Observable<Response<List<Chapter>>> =
            mApiService.getWeChatListData()

    override fun getWeChatArticleListData(id: Int, pageNum: Int): Observable<Response<ArticleList>> =
            mApiService.getWeChatArticleListData(id, pageNum)

    companion object {

        @Volatile
        private var mInstance: HttpDataSourceImpl? = null

        fun getInstance(apiService: ApiService) =
                mInstance ?: synchronized(this) {
                    mInstance ?: HttpDataSourceImpl(apiService).apply { mInstance = this }
                }
    }
}