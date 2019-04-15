package com.github.cyc.wanandroid.module.main.fragment

import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentNavigationBinding
import com.github.cyc.wanandroid.module.main.viewmodel.NavigationViewModel

/**
 * 导航tab
 */
class NavigationFragment : BaseFragment<FragmentNavigationBinding, NavigationViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_navigation

    override fun initViewModel() {
        mViewModel = NavigationViewModel()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {

    }

    companion object {

        fun newInstance() = NavigationFragment()
    }
}