package com.github.cyc.wanandroid.http

/**
 * HTTP码
 */
object HttpCode {

    /**
     * 成功
     */
    const val SUCCESS = 0

    /**
     * 未知错误
     */
    const val ERROR_UNKNOWN = 1000

    /**
     * HTTP错误
     */
    const val ERROR_HTTP = 1001

    /**
     * 网络错误
     */
    const val ERROR_NETWORK = 1002

    /**
     * 解析错误
     */
    const val ERROR_PARSE = 1003

    /**
     * SSL错误
     */
    const val ERROR_SSL = 1004

    /**
     * 登录失效，需要重新登录
     */
    const val ERROR_LOGIN_INVALID = -1001

}