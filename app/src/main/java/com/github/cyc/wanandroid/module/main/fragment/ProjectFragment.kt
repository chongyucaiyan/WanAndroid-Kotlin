package com.github.cyc.wanandroid.module.main.fragment

import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentProjectBinding
import com.github.cyc.wanandroid.module.main.viewmodel.ProjectViewModel

/**
 * 项目tab
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_project

    override fun initViewModel() {
        mViewModel = ProjectViewModel()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {

    }

    companion object {

        fun newInstance() = ProjectFragment()
    }
}