package id.muiz.githubuserapi.feature.following

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
import id.muiz.githubuserapi.data.entity.UserFollowingResponseItem
import id.muiz.githubuserapi.feature.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_following.*
import javax.inject.Inject

class FollowingFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : FollowingViewModel

    private var lists = mutableListOf<UserFollowingResponseItem>()

    private val followingAdapter: FollowingAdapter by lazy {
        FollowingAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
        handleUserName()
        initObserver()
        initRecyclerView()
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FollowingViewModel::class.java]
    }

    private fun handleUserName() {
        val activity = activity as DetailActivity
        val username : String? = activity.getUsername()
        viewModel.getUserFollowing(username!!)
    }

    private fun initRecyclerView() {
        rvFollowing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvFollowing.adapter = followingAdapter
    }

    private fun initObserver() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            handleStateLoading(it)
        })
        viewModel.resultUserFollowing.observe(viewLifecycleOwner, Observer {
            handleResultUserFollowing(it)
        })
    }

    private fun handlingEmptyFollowing(data: List<UserFollowingResponseItem>){
        if (data.isEmpty()) {
            rvFollowing.setGone()
        } else {
            rvFollowing.setVisible()
        }
    }

    private fun handleResultUserFollowing(data: List<UserFollowingResponseItem>) {
        handlingEmptyFollowing(data)
        lists.clear()
        lists.addAll(data)
        followingAdapter.setItems(lists)
    }

    private fun handleStateLoading(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading) {
            progressBarFollowing.setVisible()
            rvFollowing.setGone()
        } else {
            progressBarFollowing.setGone()
            rvFollowing.setVisible()
        }
    }

}