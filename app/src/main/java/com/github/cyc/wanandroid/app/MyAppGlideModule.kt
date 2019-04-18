package com.github.cyc.wanandroid.app

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 * Glide模块。配置Glide
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
                .setMemoryCache(LruResourceCache(SIZE_MEMORY_CACHE))
                .setDiskCache(InternalCacheDiskCacheFactory(context, SIZE_DISK_CACHE))
    }

    companion object {

        private const val SIZE_MEMORY_CACHE = 1024 * 1024 * 20L
        private const val SIZE_DISK_CACHE = 1024 * 1024 * 100L
    }
}