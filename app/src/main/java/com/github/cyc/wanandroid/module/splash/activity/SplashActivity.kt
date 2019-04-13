package com.github.cyc.wanandroid.module.splash.activity

import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.activity.BaseActivity
import com.github.cyc.wanandroid.databinding.ActivitySplashBinding
import com.github.cyc.wanandroid.module.main.activity.MainActivity
import com.github.cyc.wanandroid.module.splash.viewmodel.SplashViewModel
import com.github.cyc.wanandroid.navigator.MainNavigator

/**
 * 闪屏页
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), MainNavigator {

    override fun getLayoutResId() = R.layout.activity_splash

    override fun initViewModel() {
        mViewModel = SplashViewModel(this)
    }

    override fun bindViewModel() {

    }

    override fun init() {
        mViewModel?.startTimer()
    }

    override fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }
}