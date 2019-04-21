package com.github.cyc.wanandroid.module.main.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.cjj.MaterialRefreshLayout
import com.cjj.MaterialRefreshListener
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Constant
import com.github.cyc.wanandroid.app.Injection
import com.github.cyc.wanandroid.app.ScrollToTop
import com.github.cyc.wanandroid.base.fragment.BaseLazyFragment
import com.github.cyc.wanandroid.databinding.FragmentProjectListBinding
import com.github.cyc.wanandroid.module.main.adapter.ProjectListAdapter
import com.github.cyc.wanandroid.module.main.viewmodel.ProjectListViewModel

/**
 * 项目列表页
 */
class ProjectListFragment : BaseLazyFragment<FragmentProjectListBinding, ProjectListViewModel>(),
        ScrollToTop {

    private var mId = -1

    override fun handleArguments(args: Bundle) {
        mId = args.getInt(Constant.KEY_ID)
    }

    override fun getLayoutResId() = R.layout.fragment_project_list

    override fun initViewModel() {
        mViewModel = ProjectListViewModel(Injection.provideDataManager())
        mViewModel?.setId(mId)
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        initRefreshLayout()
        initRecyclerView()
    }

    override fun onLazyLoad() {
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
            adapter = ProjectListAdapter(mViewModel!!.dataList)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun scrollToTop() {
        mDataBinding.rvRecyclerView.smoothScrollToPosition(0)
    }

    companion object {

        fun newInstance(id: Int): ProjectListFragment {
            val bundle = Bundle()
            bundle.putInt(Constant.KEY_ID, id)

            val fragment = ProjectListFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}