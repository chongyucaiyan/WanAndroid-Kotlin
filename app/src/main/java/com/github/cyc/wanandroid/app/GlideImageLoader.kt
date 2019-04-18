package com.github.cyc.wanandroid.app

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader

/**
 * Glide图片加载器。供Banner控件使用
 */
class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        if (context != null && imageView != null) {
            GlideApp.with(context).load(path).into(imageView)
        }
    }
}