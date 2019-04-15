package com.github.cyc.wanandroid.module.main.fragment

import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentWeChatBinding
import com.github.cyc.wanandroid.module.main.viewmodel.WeChatViewModel

/**
 * 公众号tab
 */
class WeChatFragment : BaseFragment<FragmentWeChatBinding, WeChatViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_we_chat

    override fun initViewModel() {
        mViewModel = WeChatViewModel()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {

    }

    companion object {

        fun newInstance() = WeChatFragment()
    }
}