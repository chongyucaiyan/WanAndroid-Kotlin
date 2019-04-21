package com.github.cyc.wanandroid.module.main.fragment

import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Injection
import com.github.cyc.wanandroid.app.ScrollToTop
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentProjectBinding
import com.github.cyc.wanandroid.module.main.adapter.ProjectListPagerAdapter
import com.github.cyc.wanandroid.module.main.viewmodel.ProjectViewModel

/**
 * 项目tab
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>(), ScrollToTop {

    private lateinit var mPagerAdapter: ProjectListPagerAdapter

    override fun getLayoutResId() = R.layout.fragment_project

    override fun initViewModel() {
        mViewModel = ProjectViewModel(Injection.provideDataManager())
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        initView()
        mViewModel?.loadData()
    }

    override fun isSupportLoad() = true

    private fun initView() {
        mPagerAdapter = ProjectListPagerAdapter(childFragmentManager)
        mDataBinding.vpViewPager.adapter = mPagerAdapter
        mDataBinding.tlTabLayout.setupWithViewPager(mDataBinding.vpViewPager)
    }

    override fun scrollToTop() {
        val fragment = mPagerAdapter.getItem(mDataBinding.vpViewPager.currentItem)
        if (fragment is ScrollToTop) {
            fragment.scrollToTop()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPagerAdapter.release()
    }

    companion object {

        fun newInstance() = ProjectFragment()
    }
}