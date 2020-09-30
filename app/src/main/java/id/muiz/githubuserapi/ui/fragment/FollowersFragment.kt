package id.muiz.githubuserapi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header

import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.adapter.FollowerAdapter
import id.muiz.githubuserapi.model.FollowerItems
import id.muiz.githubuserapi.model.UserItems
import kotlinx.android.synthetic.main.fragment_followers.*
import org.json.JSONArray
import org.json.JSONObject

class FollowersFragment : Fragment() {

    private var listFollowerItems: ArrayList<FollowerItems> = ArrayList()
    private lateinit var adapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FollowerAdapter(listFollowerItems)
        listFollowerItems.clear()
        val dataUser = activity!!.intent.getParcelableExtra<UserItems>("data")
        setFollower(dataUser?.username.toString())
    }

    private fun setFollower(username: String) {
        // Request API
        showLoading(true)
        val apiKey = "a9baeefece821217ba18aa6860630dfd0f268f04"
        val url = "https://api.github.com/users/$username/followers"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", apiKey)
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                showLoading(false)
                val result = String(responseBody)
                try {
                    //parsing json
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val user = jsonArray.getJSONObject(i)
                        val userName: String = user.getString("login")
                        setDetailUser(userName)
                    }
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    fun setDetailUser(username: String) {
        // Request API
        showLoading(true)
        val apiKey = "a9baeefece821217ba18aa6860630dfd0f268f04"
        val url = "https://api.github.com/users/$username"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", apiKey)
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                showLoading(false)
                val result = String(responseBody)
                try {
                    //parsing json
                    val user = JSONObject(result)
                    val followerItems = FollowerItems()
                    followerItems.id = user.getInt("id")
                    followerItems.username = user.getString("login")
                    followerItems.foto = user.getString("avatar_url")
                    followerItems.name = user.getString("name")
                    followerItems.company = user.getString("company")
                    followerItems.location = user.getString("location")
                    followerItems.repository = user.getInt("public_repos")
                    followerItems.followers = user.getInt("followers")
                    followerItems.following = user.getInt("following")
                    listFollowerItems.add(followerItems)
                    showRecyclerList()
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    private fun showRecyclerList() {
        rvFollowers.layoutManager = LinearLayoutManager(activity)
        val listDataAdapter =
            FollowerAdapter(listFollowerItems)
        rvFollowers.adapter = adapter

        listDataAdapter.setOnItemClickCallback(object :
            FollowerAdapter.OnItemClickCallback {
            override fun onItemClicked(followerItems: FollowerItems) {
                //DO NOTHING
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBarFollowers.visibility = View.VISIBLE
        } else {
            progressBarFollowers.visibility = View.GONE
        }
    }

}
