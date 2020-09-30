package id.muiz.githubuserapi.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.ui.fragment.FollowersFragment
import id.muiz.githubuserapi.ui.fragment.FollowingFragment

class ViewPagerDetailAdapter(private val mContext: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = listOf(
        FollowersFragment(),
        FollowingFragment()
    )

    private val tabTitle = intArrayOf(
        R.string.followers,
        R.string.following
    )

    override fun getItem(position: Int): Fragment {
        return  pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitle[position])
    }
}