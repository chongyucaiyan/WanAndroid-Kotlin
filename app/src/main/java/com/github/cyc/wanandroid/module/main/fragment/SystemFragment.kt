package com.github.cyc.wanandroid.module.main.fragment

import android.support.v7.widget.LinearLayoutManager
import com.cjj.MaterialRefreshLayout
import com.cjj.MaterialRefreshListener
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Injection
import com.github.cyc.wanandroid.app.ScrollToTop
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentSystemBinding
import com.github.cyc.wanandroid.module.main.adapter.SystemListAdapter
import com.github.cyc.wanandroid.module.main.viewmodel.SystemViewModel

/**
 * 体系tab
 */
class SystemFragment : BaseFragment<FragmentSystemBinding, SystemViewModel>(), ScrollToTop {

    override fun getLayoutResId() = R.layout.fragment_system

    override fun initViewModel() {
        mViewModel = SystemViewModel(Injection.provideDataManager())
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
        })
    }

    private fun initRecyclerView() {
        mDataBinding.rvRecyclerView.run {
            adapter = SystemListAdapter(mViewModel!!.dataList)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun scrollToTop() {
        mDataBinding.rvRecyclerView.smoothScrollToPosition(0)
    }

    companion object {

        fun newInstance() = SystemFragment()
    }
}