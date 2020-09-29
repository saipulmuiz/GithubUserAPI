package id.muiz.githubuserapi

import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Useradapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = Useradapter(this::onItemClick)
        adapter.notifyDataSetChanged()

        lv_user.layoutManager = LinearLayoutManager(this)
        lv_user.adapter = adapter

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
        //val moveDetail = Intent(this, DetailActivity::class.java)
        //moveDetail.putExtra("data", userItems)
        //startActivity(moveDetail)
        Toast.makeText(this, "Pilih ${userItems.username}", Toast.LENGTH_SHORT).show()
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

}
