package com.github.cyc.wanandroid.module.main.fragment

import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentSystemBinding
import com.github.cyc.wanandroid.module.main.viewmodel.SystemViewModel

/**
 * 体系tab
 */
class SystemFragment : BaseFragment<FragmentSystemBinding, SystemViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_system

    override fun initViewModel() {
        mViewModel = SystemViewModel()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {

    }

    companion object {

        fun newInstance() = SystemFragment()
    }
}