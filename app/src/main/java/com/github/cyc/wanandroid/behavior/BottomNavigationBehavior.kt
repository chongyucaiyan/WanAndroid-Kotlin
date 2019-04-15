package com.github.cyc.wanandroid.behavior

import android.animation.ObjectAnimator
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import com.github.cyc.wanandroid.app.NoProguard

/**
 * 底部导航的Behavior
 */
class BottomNavigationBehavior(context: Context, attrs: AttributeSet?)
    : CoordinatorLayout.Behavior<View>(context, attrs), NoProguard {

    private var mAnimatorOut: ObjectAnimator? = null

    private var mAnimatorIn: ObjectAnimator? = null

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View,
                                     directTargetChild: View, target: View, axes: Int, type: Int) =
            axes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View,
                                   target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        // 上滑隐藏底部导航，下滑显示底部导航
        if (dy > 0 && child.translationY == 0f) {
            if (mAnimatorOut == null) {
                mAnimatorOut = ObjectAnimator.ofFloat(child, "translationY", 0f, child.height.toFloat())
                        .setDuration(300)
            }

            mAnimatorOut?.run {
                if (!isRunning) {
                    start()
                }
            }
        } else if (dy < 0 && child.translationY == child.height.toFloat()) {
            if (mAnimatorIn == null) {
                mAnimatorIn = ObjectAnimator.ofFloat(child, "translationY", child.height.toFloat(), 0f)
                        .setDuration(300)
            }

            mAnimatorIn?.run {
                if (!isRunning) {
                    start()
                }
            }
        }
    }
}