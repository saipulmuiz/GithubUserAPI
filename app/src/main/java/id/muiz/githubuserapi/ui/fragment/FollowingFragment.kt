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
import id.muiz.githubuserapi.adapter.FollowingAdapter
import id.muiz.githubuserapi.model.FollowingItems
import id.muiz.githubuserapi.model.UserItems
import kotlinx.android.synthetic.main.fragment_following.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class FollowingFragment : Fragment() {

    private var listFollowingItems: ArrayList<FollowingItems> = ArrayList()
    private lateinit var adapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FollowingAdapter(listFollowingItems)
        listFollowingItems.clear()
        val dataUser = activity!!.intent.getParcelableExtra<UserItems>("data")
        setFollowing(dataUser?.username.toString())
    }

    private fun setFollowing(username: String) {
        // Request API
        showLoading(true)
        val apiKey = "a9baeefece821217ba18aa6860630dfd0f268f04"
        val url = "https://api.github.com/users/$username/following"
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
                    val followerItems = FollowingItems()
                    followerItems.id = user.getInt("id")
                    followerItems.username = user.getString("login")
                    followerItems.foto = user.getString("avatar_url")
                    followerItems.name = user.getString("name")
                    followerItems.company = user.getString("company")
                    followerItems.location = user.getString("location")
                    followerItems.repository = user.getInt("public_repos")
                    followerItems.followers = user.getInt("followers")
                    followerItems.following = user.getInt("following")
                    listFollowingItems.add(followerItems)
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
        rvFollowing.layoutManager = LinearLayoutManager(activity)
        val listDataAdapter =
            FollowingAdapter(listFollowingItems)
        rvFollowing.adapter = adapter

        listDataAdapter.setOnItemClickCallback(object :
            FollowingAdapter.OnItemClickCallback {
            override fun onItemClicked(followingItems: FollowingItems) {
                //DO NOTHING
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBarFollowing.visibility = View.VISIBLE
        } else {
            progressBarFollowing.visibility = View.GONE
        }
    }

}
