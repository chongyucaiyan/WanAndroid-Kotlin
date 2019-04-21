package com.github.cyc.wanandroid.module.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.github.cyc.wanandroid.base.adapter.BasePagerAdapter
import com.github.cyc.wanandroid.http.model.Chapter
import com.github.cyc.wanandroid.module.main.fragment.ProjectListFragment

/**
 * 项目列表PagerAdapter
 */
class ProjectListPagerAdapter(fragmentManager: FragmentManager)
    : BasePagerAdapter<Chapter>(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        var fragment = mFragmentMap[position]
        if (fragment == null) {
            fragment = ProjectListFragment.newInstance(mDataList!![position].id)
            mFragmentMap.put(position, fragment)
        }

        return fragment
    }

    override fun getPageTitle(position: Int) = mDataList!![position].name
}