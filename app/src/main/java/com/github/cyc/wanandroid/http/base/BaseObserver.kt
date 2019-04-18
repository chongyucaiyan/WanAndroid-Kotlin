package com.github.cyc.wanandroid.http.base

import android.databinding.ObservableField
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.enums.LoadState
import com.github.cyc.wanandroid.http.HttpCode
import com.github.cyc.wanandroid.http.model.Response
import com.github.cyc.wanandroid.utils.ToastUtils
import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import io.reactivex.observers.ResourceObserver
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 * Observer的基类
 *
 * @param <T> 具体的网络请求响应model
 */
abstract class BaseObserver<T>(
        private val mLoadState: ObservableField<LoadState>? = null,
        private val mShowError: Boolean = true
) : ResourceObserver<Response<T>>(), ObserverX<T> {

    final override fun onNext(response: Response<T>) {
        val errorCode = response.errorCode
        if (errorCode == HttpCode.SUCCESS) {
            // 服务端返回成功
            onNextX(response.data)
        } else {
            val errorMsg = response.errorMsg
            ToastUtils.show(errorMsg)
            if (mShowError) {
                mLoadState?.set(LoadState.ERROR)
            }

            // 公共错误逻辑处理
            when (errorCode) {
                HttpCode.ERROR_LOGIN_INVALID -> {
                }

                else -> {
                }
            }

            Logger.i("errorCode: %d, errorMsg: %s", errorCode, errorMsg)

            // 服务端返回错误
            onErrorX(errorCode, errorMsg)
        }
    }

    final override fun onError(e: Throwable) {
        var errorCode = HttpCode.ERROR_UNKNOWN
        val errorMsg = e.message ?: "unknown"

        // 公共错误逻辑处理
        when (e) {
            is HttpException -> {
                errorCode = HttpCode.ERROR_HTTP
                ToastUtils.show(R.string.error_http)
                if (mShowError) {
                    mLoadState?.set(LoadState.ERROR)
                }
            }

            is ConnectException, is UnknownHostException -> {
                errorCode = HttpCode.ERROR_NETWORK
                ToastUtils.show(R.string.error_network)
                if (mShowError) {
                    mLoadState?.set(LoadState.NO_NETWORK)
                }
            }

            is JsonParseException, is JSONException -> {
                errorCode = HttpCode.ERROR_PARSE
                ToastUtils.show(R.string.error_parse)
                if (mShowError) {
                    mLoadState?.set(LoadState.ERROR)
                }
            }

            is SSLHandshakeException -> {
                errorCode = HttpCode.ERROR_SSL
                ToastUtils.show(R.string.error_ssl)
                if (mShowError) {
                    mLoadState?.set(LoadState.ERROR)
                }
            }

            else -> {
                ToastUtils.show(R.string.error_unknown)
                if (mShowError) {
                    mLoadState?.set(LoadState.ERROR)
                }
            }
        }

        Logger.i("errorCode: %d, errorMsg: %s", errorCode, errorMsg)

        onErrorX(errorCode, errorMsg)
    }

    final override fun onComplete() {
        onCompleteX()
    }

    override fun onErrorX(errorCode: Int, errorMsg: String) {

    }

    override fun onCompleteX() {

    }
}