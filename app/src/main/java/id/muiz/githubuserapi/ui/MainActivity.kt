package id.muiz.githubuserapi.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.muiz.githubuserapi.MainViewModel
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.adapter.Useradapter
import id.muiz.githubuserapi.model.UserItems
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Useradapter
    private lateinit var mainViewModel: MainViewModel
    private var doubleBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayout3.visibility = View.GONE

        adapter = Useradapter(this::onItemClick)
        adapter.notifyDataSetChanged()

        rv_user.layoutManager = LinearLayoutManager(this)
        rv_user.adapter = adapter

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val username = searchUser.query
                showLoading(true)
                mainViewModel.setUser(username.toString())
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })

        mainViewModel.getUsers().observe(this, Observer { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
                showLoading(false)
            }
        })

    }

    private fun onItemClick(userItems: UserItems) {
        val username = userItems.username
        mainViewModel.setDetailUser(username.toString())
        val moveDetail = Intent(this, DetailActivity::class.java)
        moveDetail.putExtra("data", userItems)
        startActivity(moveDetail)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
            linearLayout3.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            linearLayout3.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        if (doubleBack) {
            super.onBackPressed()
            return
        }

        this.doubleBack = true
        Toast.makeText(this, "Tekan sekali lagi untuk keluar!", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBack = false }, 2000)

    }

}
