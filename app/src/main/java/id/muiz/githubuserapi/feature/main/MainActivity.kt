package id.muiz.githubuserapi.feature.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.core.base.BaseActivity
import id.muiz.githubuserapi.core.state.LoaderState
import id.muiz.githubuserapi.core.util.setGone
import id.muiz.githubuserapi.core.util.setVisible
import id.muiz.githubuserapi.core.util.toast
import id.muiz.githubuserapi.data.entity.UserSearchResponseItem
import id.muiz.githubuserapi.feature.favorit.FavoritActivity
import id.muiz.githubuserapi.feature.settings.PengaturanActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainViewModel

    private val items = mutableListOf<UserSearchResponseItem>()

    private var doubleBack = false

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchUsers()
        initViewModels()
        initRecyclerView()
        initObserver()
        btnLove.setOnClickListener {
            startActivity(Intent(this, FavoritActivity::class.java))
        }
        btnSettings.setOnClickListener {
            startActivity(Intent(this, PengaturanActivity::class.java))
        }
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun searchUsers() {
        searchUser.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()) {
                        items.clear()
                        viewModel.getUserFromApi(query)
                        searchUser.clearFocus()
                    } else {
                        searchUser.clearFocus()
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = false
        })

        searchUser.setOnCloseListener(object: android.widget.SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                items.clear()
                mainAdapter.clearItems()
                return true
            }
        })

    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer {
            it?.let {
                handleStateLoading(it)
            }
        })
        viewModel.resultUserApi.observe(this, Observer {
            it?.let {
                handleUserFromApi(it)
            }
        })
        viewModel.networkError.observe(this, Observer {
            it?.let {
                handleStateInternet(it)
            }
        })
    }

    private fun initRecyclerView() {
        rv_user.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_user.adapter = mainAdapter
        mainAdapter.setActivity(this)
    }

    private fun handleUserFromApi(result: List<UserSearchResponseItem>) {
        items.clear()
        items.addAll(result)
        mainAdapter.setItems(items)
    }

    private fun handleStateInternet(error: Boolean) {
        if(error) {
            progressBar.setVisible()
            rv_user.setGone()
        } else {
            progressBar.setGone()
            rv_user.setVisible()
        }
    }

    private fun handleStateLoading(loading: LoaderState) {
        if(loading is LoaderState.ShowLoading) {
            progressBar.setVisible()
            rv_user.setGone()
        } else {
            progressBar.setGone()
            rv_user.setVisible()
        }
    }

    override fun onBackPressed() {
        if (doubleBack) {
            super.onBackPressed()
            return
        }

        this.doubleBack = true
        toast(getString(R.string.exit_notif))

        Handler().postDelayed({ doubleBack = false }, 2000)

    }
}
