package com.github.cyc.wanandroid.module.details.activity

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.MenuItem
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.LinearLayout
import com.github.cyc.wanandroid.R
import com.github.cyc.wanandroid.app.Constant
import com.github.cyc.wanandroid.app.Injection
import com.github.cyc.wanandroid.base.activity.BaseActivity
import com.github.cyc.wanandroid.databinding.ActivityDetailsBinding
import com.github.cyc.wanandroid.module.details.viewmodel.DetailsViewModel
import com.just.agentweb.AgentWeb

/**
 * 详情页
 */
class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {

    private lateinit var mAgentWeb: AgentWeb

    private lateinit var mUrl: String

    override fun handleIntent(intent: Intent) {
        mUrl = intent.getStringExtra(Constant.KEY_URL)
    }

    override fun getLayoutResId() = R.layout.activity_details

    override fun initViewModel() {
        mViewModel = DetailsViewModel(Injection.provideDataManager())
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
        initToolbar()
        initWebView()
    }

    private fun initToolbar() {
        setSupportActionBar(mDataBinding.iToolbar.tbToolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initWebView() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mDataBinding.llRoot, LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .createAgentWeb()
                .ready()
                .go(mUrl)
    }

    private val mWebChromeClient = object : WebChromeClient() {

        override fun onReceivedTitle(view: WebView?, title: String?) {
            mDataBinding.iToolbar.tbToolbar.title = title
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

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (!mAgentWeb.back()) {
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?) =
            if (mAgentWeb.handleKeyEvent(keyCode, event))
                true
            else
                super.onKeyDown(keyCode, event)

    companion object {

        fun start(context: Context, url: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(Constant.KEY_URL, url)
            context.startActivity(intent)
        }
    }
}