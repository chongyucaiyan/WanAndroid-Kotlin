package com.github.cyc.wanandroid.module.main.activity

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Constant
import com.github.cyc.wanandroid.app.ScrollToTop
import com.github.cyc.wanandroid.base.activity.BaseActivity
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel
import com.github.cyc.wanandroid.databinding.ActivitySystemDetailsBinding
import com.github.cyc.wanandroid.http.model.Chapter
import com.github.cyc.wanandroid.module.main.adapter.ArticleListPagerAdapter

/**
 * 体系详情页
 */
class SystemDetailsActivity : BaseActivity<ActivitySystemDetailsBinding, BaseViewModel>(),
        ScrollToTop {

    private lateinit var mPagerAdapter: ArticleListPagerAdapter

    private lateinit var mChapter: Chapter

    override fun handleIntent(intent: Intent) {
        mChapter = intent.getSerializableExtra(Constant.KEY_CHAPTER) as Chapter
    }

    override fun getLayoutResId() = R.layout.activity_system_details

    override fun initViewModel() {

    }

    override fun bindViewModel() {

    }

    override fun init() {
        initToolbar()
        initViewPager()
        initFloatingActionButton()
    }

    private fun initToolbar() {
        setSupportActionBar(mDataBinding.tbToolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayHomeAsUpEnabled(true)
            title = mChapter.name
        }
    }

    private fun initViewPager() {
        mPagerAdapter = ArticleListPagerAdapter(supportFragmentManager)
        mPagerAdapter.setDataList(mChapter.children)
        mDataBinding.vpViewPager.adapter = mPagerAdapter
        mDataBinding.tlTabLayout.setupWithViewPager(mDataBinding.vpViewPager)
    }

    private fun initFloatingActionButton() {
        mDataBinding.fabScrollToTop.setOnClickListener { scrollToTop() }
    }

    override fun scrollToTop() {
        val fragment = mPagerAdapter.getItem(mDataBinding.vpViewPager.currentItem)
        if (fragment is ScrollToTop) {
            fragment.scrollToTop()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }

    override fun onDestroy() {
        super.onDestroy()
        mPagerAdapter.release()
    }

    companion object {

        fun start(context: Context, chapter: Chapter) {
            val intent = Intent(context, SystemDetailsActivity::class.java)
            intent.putExtra(Constant.KEY_CHAPTER, chapter)
            context.startActivity(intent)
        }
    }
}