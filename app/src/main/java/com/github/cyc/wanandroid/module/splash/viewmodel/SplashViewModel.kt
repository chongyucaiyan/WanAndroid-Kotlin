package com.github.cyc.wanandroid.module.splash.viewmodel

import com.github.cyc.wanandroid.app.Constant
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.navigator.MainNavigator
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * 闪屏页的ViewModel
 */
class SplashViewModel(private val mMainNavigator: MainNavigator) : BaseViewModel() {

    fun startTimer() {
        addDisposable(Observable.timer(Constant.SPLASH_TIME, TimeUnit.MILLISECONDS)
                .subscribe { mMainNavigator.startMainActivity() })
    }
}