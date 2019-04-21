package com.github.cyc.wanandroid.module.main.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Injection
import com.github.cyc.wanandroid.app.ScrollToTop
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentNavigationBinding
import com.github.cyc.wanandroid.module.main.adapter.NavigationListAdapter
import com.github.cyc.wanandroid.module.main.viewmodel.NavigationViewModel
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView

/**
 * 导航tab
 */
class NavigationFragment : BaseFragment<FragmentNavigationBinding, NavigationViewModel>(), ScrollToTop {

    private lateinit var mTabSelectedListener: VerticalTabLayout.OnTabSelectedListener

    private lateinit var mScrollListener: RecyclerView.OnScrollListener

    private lateinit var mLayoutManager: LinearLayoutManager

    private var mNeedScroll = false

    private var mToPosition = -1

    override fun getLayoutResId() = R.layout.fragment_navigation

    override fun initViewModel() {
        mViewModel = NavigationViewModel(Injection.provideDataManager())
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        initTabLayout()
        initRecyclerView()
        mViewModel?.loadData()
    }

    override fun isSupportLoad() = true

    private fun initTabLayout() {
        mTabSelectedListener = object : VerticalTabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabView, position: Int) {
                smoothScrollToPosition(position)
            }

            override fun onTabReselected(tab: TabView, position: Int) {

            }
        }

        mDataBinding.vtlTabLayout.addOnTabSelectedListener(mTabSelectedListener)
    }

    private fun initRecyclerView() {
        mLayoutManager = LinearLayoutManager(context)
        mScrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (mNeedScroll) {
                        mNeedScroll = false
                        smoothScrollToPosition(mToPosition)
                    } else {
                        setTabSelected()
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        }

        mDataBinding.rvRecyclerView.run {
            addOnScrollListener(mScrollListener)
            layoutManager = mLayoutManager
            adapter = NavigationListAdapter(mViewModel!!.dataList)
        }
    }

    private fun smoothScrollToPosition(position: Int) {
        val firstPosition = mLayoutManager.findFirstVisibleItemPosition()
        val lastPosition = mLayoutManager.findLastVisibleItemPosition()

        if (position < firstPosition) {
            mDataBinding.rvRecyclerView.smoothScrollToPosition(position)
        } else if (position <= lastPosition) {
            val movePosition = position - firstPosition
            if (movePosition < mLayoutManager.childCount) {
                val top = mLayoutManager.getChildAt(movePosition)?.top ?: 0
                mDataBinding.rvRecyclerView.smoothScrollBy(0, top)
            }
        } else {
            mDataBinding.rvRecyclerView.smoothScrollToPosition(position)
            mToPosition = position
            mNeedScroll = true
        }
    }

    private fun setTabSelected() {
        val firstPosition = mLayoutManager.findFirstVisibleItemPosition()
        mDataBinding.vtlTabLayout.setTabSelected(firstPosition, false)
    }

    override fun scrollToTop() {
        mDataBinding.rvRecyclerView.smoothScrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mDataBinding.vtlTabLayout.removeOnTabSelectedListener(mTabSelectedListener)
        mDataBinding.rvRecyclerView.removeOnScrollListener(mScrollListener)
    }

    companion object {

        fun newInstance() = NavigationFragment()
    }
}