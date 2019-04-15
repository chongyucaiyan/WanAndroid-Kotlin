package com.github.cyc.wanandroid.module.main.fragment

import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentHomepageBinding
import com.github.cyc.wanandroid.module.main.viewmodel.HomepageViewModel

/**
 * 首页tab
 */
class HomepageFragment : BaseFragment<FragmentHomepageBinding, HomepageViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_homepage

    override fun initViewModel() {
        mViewModel = HomepageViewModel()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {

    }

    companion object {

        fun newInstance() = HomepageFragment()
    }
}