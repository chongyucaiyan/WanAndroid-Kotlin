package com.github.cyc.wanandroid.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import com.github.cyc.wanandroid.app.NoProguard

/**
 * 悬浮按钮的Behavior
 */
class FloatingActionButtonBehavior(context: Context, attrs: AttributeSet?)
    : FloatingActionButton.Behavior(context, attrs), NoProguard {

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton,
                                     directTargetChild: View, target: View, axes: Int, type: Int) =
            axes == ViewCompat.SCROLL_AXIS_VERTICAL

    @SuppressWarnings("RestrictedApi")
    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View,
                                dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        // 上滑隐藏悬浮按钮，下滑显示悬浮按钮
        if (dyConsumed > 0 && child.visibility == View.VISIBLE) {
            child.visibility = View.INVISIBLE
        } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {
            child.show()
        }
    }
}