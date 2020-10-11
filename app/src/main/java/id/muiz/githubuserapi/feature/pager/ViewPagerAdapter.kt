package id.muiz.githubuserapi.feature.pager

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.feature.follower.FollowersFragment
import id.muiz.githubuserapi.feature.following.FollowingFragment

class ViewPagerAdapter(
    private val mContext: Context,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.followers, R.string.following)

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position) {
            0 -> fragment =
                FollowersFragment()
            1 -> fragment =
                FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int = 2
}