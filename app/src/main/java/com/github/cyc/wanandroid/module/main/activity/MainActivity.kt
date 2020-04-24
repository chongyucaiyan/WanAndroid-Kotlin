package com.github.cyc.wanandroid.module.main.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.util.SparseArray
import android.view.MenuItem
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Constant
import com.github.cyc.wanandroid.app.ScrollToTop
import com.github.cyc.wanandroid.base.activity.BaseActivity
import com.github.cyc.wanandroid.databinding.ActivityMainBinding
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity
import com.github.cyc.wanandroid.module.main.fragment.*
import com.github.cyc.wanandroid.module.main.viewmodel.MainViewModel
import com.github.cyc.wanandroid.utils.ToastUtils

/**
 * 主页
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mFragmentMap = SparseArray<Fragment>()

    private var mLastIndex = -1

    private var mExitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
    }

    override fun getLayoutResId() = R.layout.activity_main

    override fun initViewModel() {
        mViewModel = MainViewModel()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        initToolbar()
        initBottomNavigationView()
        initDrawerNavigationView()
        initFloatingActionButton()
        switchFragment(INDEX_HOMEPAGE)
    }

    private fun initToolbar() {
        setSupportActionBar(mDataBinding.iToolbar.tbToolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.app_name)
        }
    }

    private fun initBottomNavigationView() {
        mDataBinding.bnvBottomNav.labelVisibilityMode = 1
        mDataBinding.bnvBottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tab_homepage -> {
                    mDataBinding.iToolbar.tbToolbar.setTitle(R.string.app_name)
                    switchFragment(INDEX_HOMEPAGE)
                    true
                }

                R.id.tab_system -> {
                    mDataBinding.iToolbar.tbToolbar.setTitle(R.string.system)
                    switchFragment(INDEX_SYSTEM)
                    true
                }

                R.id.tab_we_chat -> {
                    mDataBinding.iToolbar.tbToolbar.setTitle(R.string.we_chat)
                    switchFragment(INDEX_WE_CHAT)
                    true
                }

                R.id.tab_navigation -> {
                    mDataBinding.iToolbar.tbToolbar.setTitle(R.string.navigation)
                    switchFragment(INDEX_NAVIGATION)
                    true
                }

                R.id.tab_project -> {
                    mDataBinding.iToolbar.tbToolbar.setTitle(R.string.project)
                    switchFragment(INDEX_PROJECT)
                    true
                }

                else -> false
            }
        }
    }

    private fun initDrawerNavigationView() {
        mDataBinding.nvDrawerNav.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_settings -> {
                    mDataBinding.dlDrawer.closeDrawers()
                    true
                }

                R.id.nav_about -> {
                    DetailsActivity.start(this, Constant.ABOUT_URL)
                    mDataBinding.dlDrawer.closeDrawers()
                    true
                }

                else -> false
            }
        }
    }

    private fun initFloatingActionButton() {
        mDataBinding.fabScrollToTop.setOnClickListener {
            val fragment = getFragment(mLastIndex)
            if (fragment is ScrollToTop) {
                fragment.scrollToTop()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // Open the navigation drawer when the home icon is selected from the toolbar.
                    mDataBinding.dlDrawer.openDrawer(GravityCompat.START)
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }

    private fun getFragment(index: Int): Fragment {
        var fragment = mFragmentMap.get(index)
        if (fragment == null) {
            fragment = when (index) {
                INDEX_HOMEPAGE -> HomepageFragment.newInstance()

                INDEX_SYSTEM -> SystemFragment.newInstance()

                INDEX_WE_CHAT -> WeChatFragment.newInstance()

                INDEX_NAVIGATION -> NavigationFragment.newInstance()

                INDEX_PROJECT -> ProjectFragment.newInstance()

                else -> HomepageFragment.newInstance()
            }
            mFragmentMap.put(index, fragment)
        }

        return fragment
    }

    private fun switchFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        if (mLastIndex != -1) {
            transaction.hide(getFragment(mLastIndex))
        }
        mLastIndex = index

        val fragment = getFragment(index)
        if (!fragment.isAdded) {
            transaction.add(R.id.fl_fragment_container, fragment)
        }

        transaction.show(fragment).commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        val curTime = System.currentTimeMillis()
        if (curTime - mExitTime > Constant.EXIT_TIME) {
            ToastUtils.show(R.string.exit_tips)
            mExitTime = curTime
        } else {
            super.onBackPressed()
        }
    }

    companion object {

        private const val INDEX_HOMEPAGE = 0
        private const val INDEX_SYSTEM = 1
        private const val INDEX_WE_CHAT = 2
        private const val INDEX_NAVIGATION = 3
        private const val INDEX_PROJECT = 4

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
