package com.github.cyc.wanandroid.app

import android.app.Application
import com.github.cyc.wanandroid.BuildConfig
import com.github.cyc.wanandroid.R
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

class WanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }

        refWatcher = LeakCanary.install(this)
        // Normal app init code...

        instance = this

        initLogger()
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag(getString(R.string.app_name))
                .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {

            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    companion object {

        lateinit var instance: WanApplication
            private set

        lateinit var refWatcher: RefWatcher
            private set
    }
}