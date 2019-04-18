package com.github.cyc.wanandroid.http

import com.github.cyc.wanandroid.BuildConfig
import com.github.cyc.wanandroid.app.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit管理类
 */
object RetrofitManager {

    fun get() = RetrofitHolder.retrofit

    private fun buildRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(Constant.TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(Constant.TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(Constant.TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private object RetrofitHolder {
        val retrofit = buildRetrofit()
    }
}