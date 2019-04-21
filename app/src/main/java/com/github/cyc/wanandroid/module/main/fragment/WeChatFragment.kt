package com.github.cyc.wanandroid.module.main.fragment

import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Injection
import com.github.cyc.wanandroid.app.ScrollToTop
import com.github.cyc.wanandroid.base.fragment.BaseFragment
import com.github.cyc.wanandroid.databinding.FragmentWeChatBinding
import com.github.cyc.wanandroid.module.main.adapter.ArticleListPagerAdapter
import com.github.cyc.wanandroid.module.main.viewmodel.WeChatViewModel

/**
 * 公众号tab
 */
class WeChatFragment : BaseFragment<FragmentWeChatBinding, WeChatViewModel>(), ScrollToTop {

    private lateinit var mPagerAdapter: ArticleListPagerAdapter

    override fun getLayoutResId() = R.layout.fragment_we_chat

    override fun initViewModel() {
        mViewModel = WeChatViewModel(Injection.provideDataManager())
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
        mPagerAdapter = ArticleListPagerAdapter(childFragmentManager)
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

        fun newInstance() = WeChatFragment()
    }
}