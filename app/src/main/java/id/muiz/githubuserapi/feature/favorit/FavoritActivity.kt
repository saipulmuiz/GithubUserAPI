package id.muiz.githubuserapi.feature.favorit

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.core.base.BaseActivity
import id.muiz.githubuserapi.core.util.setGone
import id.muiz.githubuserapi.core.util.setVisible
import id.muiz.githubuserapi.data.db.entity.UserFavorite
import kotlinx.android.synthetic.main.activity_favorit.*
import kotlinx.android.synthetic.main.activity_favorit.btn_back
import javax.inject.Inject

class FavoritActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: FavoritUserViewModel

    private val listUser = mutableListOf<UserFavorite>()

    private val favoriteUserAdapter : FavoritUserAdapter by lazy {
        FavoritUserAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorit)
        initViewModels()
        initObserver()
        initRecyclerView()
        initButton()
    }

    fun initButton() {
        btn_back.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoritUserViewModel::class.java]
    }

    private fun initRecyclerView() {
        rv_favorite.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_favorite.adapter = favoriteUserAdapter
    }

    private fun initObserver() {
        viewModel.resultUserFromDb.observe(this, Observer {
            handleUserFromDb(it)
        })
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.fetchAllUserFavorite()
    }

    private fun handleUserFromDb(user: List<UserFavorite>) {
        handleEmptyUser(user)
        listUser.clear()
        listUser.addAll(user)
        favoriteUserAdapter.setItems(listUser)
    }

    private fun handleEmptyUser(user: List<UserFavorite>) {
        if (user.isEmpty()) {
            rv_favorite.setGone()
        } else {
            rv_favorite.setVisible()
        }
    }
}