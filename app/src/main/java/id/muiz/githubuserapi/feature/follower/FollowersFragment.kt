package id.muiz.githubuserapi.feature.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.core.base.BaseFragment
import id.muiz.githubuserapi.core.state.LoaderState
import id.muiz.githubuserapi.core.util.setGone
import id.muiz.githubuserapi.core.util.setVisible
import id.muiz.githubuserapi.data.entity.UserFollowersResponseItem
import id.muiz.githubuserapi.feature.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

class FollowersFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: FollowerViewModel

    private var lists = mutableListOf<UserFollowersResponseItem>()

    private val followerAdapter: FollowerAdapter by lazy {
        FollowerAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        handleUserName()
        initObserver()
        initRecyclerView()
    }

    private fun handleUserName() {
        val activity = activity as DetailActivity
        val username : String? = activity.getUsername()
        viewModel.getUserFollowers(username!!)
    }

    private fun initRecyclerView() {
        rvFollowers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvFollowers.adapter = followerAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FollowerViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.resultUserFollower.observe(viewLifecycleOwner, Observer {
            handleUserFollower(it)
        })
        viewModel.state.observe(viewLifecycleOwner, Observer {
            handleStateLoading(it)
        })
    }

    private fun handleEmptyFollower(data : List<UserFollowersResponseItem>) {
        if (data.isEmpty()) {
            rvFollowers.setGone()
        } else {
            rvFollowers.setVisible()
        }
    }

    private fun handleStateLoading(loading: LoaderState) {
        if(loading is LoaderState.ShowLoading) {
            progressBarFollowers.setVisible()
            rvFollowers.setGone()
        } else {
            progressBarFollowers.setGone()
            rvFollowers.setVisible()
        }
    }

    private fun handleUserFollower(data: List<UserFollowersResponseItem>) {
        handleEmptyFollower(data)
        lists.clear()
        lists.addAll(data)
        followerAdapter.setItems(data = lists)
    }

}
