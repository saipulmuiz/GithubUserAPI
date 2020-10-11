package id.muiz.githubuserapi.feature.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.core.base.BaseActivity
import id.muiz.githubuserapi.core.state.LoaderState
import id.muiz.githubuserapi.core.util.setGone
import id.muiz.githubuserapi.core.util.setVisible
import id.muiz.githubuserapi.core.util.toast
import id.muiz.githubuserapi.data.db.entity.UserFavorite
import id.muiz.githubuserapi.data.entity.UserDetailResponse
import id.muiz.githubuserapi.feature.pager.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.StringBuilder
import javax.inject.Inject

class DetailActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailViewModel

    private var userDetail: UserDetailResponse? = null

    private var userFavoriteEntity : UserFavorite? = null

    private var favoriteActive = false

    private var username : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        handleIntent()
        initViewModels()
        initObserver()
        fetchData()
        initButton()
        initPageAdapter()

    }

    private fun initButton() {
        btn_back.setOnClickListener {
            finish()
        }
        fabFavorite.setOnClickListener {
            setFavoriteUser()
        }
    }

    fun getUsername() : String? {
        return username
    }

    private fun initPageAdapter() {
        val sectionPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tabsRelate.setupWithViewPager(view_pager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun fetchData() {
        username?.let {
            viewModel.getUserDetailFromApi(it)
            viewModel.getFavUserByUsername(it)
        }
    }

    private fun handleIntent() {
        username = intent.getStringExtra(USERNAME_KEY) as String
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer {
            handleStateLoading(it)
        })
        viewModel.resultUserDetail.observe(this, Observer {
            handleResultUserDetail(it)
        })
        viewModel.resultUserDetailFromDb.observe(this, Observer {
            handleUserDetailFromDb(it)
        })
        viewModel.resultInsertUserDb.observe(this, Observer {
            if (it) {
                username?.let {
                    viewModel.getFavUserByUsername(it)
                }
                toast(getString(R.string.user_success))
            }
        })
        viewModel.resultDeleteFromDb.observe(this, Observer {
            if (it) {
                username?.let {
                    viewModel.getFavUserByUsername(it)
                }
                toast(getString(R.string.user_deleted))
            }
        })
    }

    private fun setFavoriteUser() {
        if (favoriteActive) {
            userFavoriteEntity?.let {
                viewModel.deleteUserFromDb(it)
            }
        }else {
            val userFavorite = userDetail?.login?.let {
                UserFavorite(
                    username = it,
                    id = userDetail?.id,
                    name = userDetail?.name,
                    foto = userDetail?.avatarUrl,
                    followingUrl = userDetail?.followingUrl,
                    company = userDetail?.company,
                    repository = userDetail?.publicRepos,
                    followersUrl = userDetail?.followersUrl,
                    followers = userDetail?.followers,
                    following = userDetail?.following,
                    location = userDetail?.location
                )
            }
            userFavorite?.let { viewModel.addUserToFavDB(it) }
        }
    }

    private fun handleUserDetailFromDb(userFavorite: List<UserFavorite>) {
        if (userFavorite.isEmpty()) {
            favoriteActive = false
            val icon = R.drawable.love
            fabFavorite.setImageResource(icon)
        }else {
            userFavoriteEntity = userFavorite.first()
            favoriteActive = true
            val icon = R.drawable.love_fill
            fabFavorite.setImageResource(icon)
        }
    }

    private fun handleStateLoading(loading: LoaderState) {
        if(loading is LoaderState.ShowLoading) {
            fabFavorite.setGone()
        } else {
            fabFavorite.setVisible()
        }
    }

    private fun handleResultUserDetail(data: UserDetailResponse) {
        userDetail = data
        tv_username.text = data.login
        nama.text = data.name
        tv_company.text = data.company
        tv_location.text = data.location
        jml_repository.text = StringBuilder(data.publicRepos.toString()).append("\nRepository")
        jml_followers.text = StringBuilder(data.followers.toString()).append("\nFollowers")
        jml_following.text = StringBuilder(data.following.toString()).append("\nFollowing")
        Glide.with(this).load(data.avatarUrl).circleCrop().into(img_foto_user_detail)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    companion object {
        const val USERNAME_KEY = "username_key"
    }
}
