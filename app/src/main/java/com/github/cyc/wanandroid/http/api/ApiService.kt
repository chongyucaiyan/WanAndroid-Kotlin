package com.github.cyc.wanandroid.http.api

import com.github.cyc.wanandroid.http.model.Article
import com.github.cyc.wanandroid.http.model.ArticleList
import com.github.cyc.wanandroid.http.model.Banner
import com.github.cyc.wanandroid.http.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 网络请求描述接口
 */
interface ApiService {

    /**
     * 获取首页Banner数据
     *
     * @return Banner数据
     */
    @GET("banner/json")
    fun getBannerData(): Observable<Response<List<Banner>>>

    /**
     * 获取首页置顶文章列表数据
     *
     * @return 置顶文章列表数据
     */
    @GET("article/top/json")
    fun getTopArticleListData(): Observable<Response<List<Article>>>

    /**
     * 获取首页文章列表数据
     *
     * @param pageNum 页数
     * @return 文章列表数据
     */
    @GET("article/list/{pageNum}/json")
    fun getArticleListData(@Path("pageNum") pageNum: Int): Observable<Response<ArticleList>>

}