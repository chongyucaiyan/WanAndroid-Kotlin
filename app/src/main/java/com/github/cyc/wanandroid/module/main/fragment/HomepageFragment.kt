package com.github.cyc.wanandroid.module.main.fragment

import android.support.v7.widget.LinearLayoutManager
import com.cjj.MaterialRefreshLayout
import com.cjj.MaterialRefreshListener
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Injection
import com.github.cyc.wanandroid.app.ScrollToTop
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentHomepageBinding
import com.github.cyc.wanandroid.module.main.adapter.ArticleListAdapter
import com.github.cyc.wanandroid.module.main.viewmodel.HomepageViewModel

/**
 * 首页tab
 */
class HomepageFragment : BaseFragment<FragmentHomepageBinding, HomepageViewModel>(), ScrollToTop {

    override fun getLayoutResId() = R.layout.fragment_homepage

    override fun initViewModel() {
        mViewModel = HomepageViewModel(Injection.provideDataManager())
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        initRefreshLayout()
        initRecyclerView()
        mViewModel?.loadData()
    }

    override fun isSupportLoad() = true

    private fun initRefreshLayout() {
        mDataBinding.mrlRefreshLayout.setMaterialRefreshListener(object : MaterialRefreshListener() {

            override fun onRefresh(materialRefreshLayout: MaterialRefreshLayout?) {
                mViewModel?.refreshData()
            }

            override fun onRefreshLoadMore(materialRefreshLayout: MaterialRefreshLayout?) {
                mViewModel?.loadMoreData()
            }
        })
    }

    private fun initRecyclerView() {
        mDataBinding.rvRecyclerView.run {
            adapter = ArticleListAdapter(mViewModel!!.dataList)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun scrollToTop() {
        mDataBinding.rvRecyclerView.smoothScrollToPosition(0)
    }

    companion object {

        fun newInstance() = HomepageFragment()
    }
}