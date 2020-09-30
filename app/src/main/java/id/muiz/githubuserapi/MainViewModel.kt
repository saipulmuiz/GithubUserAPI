package id.muiz.githubuserapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import id.muiz.githubuserapi.model.UserItems
import org.json.JSONObject

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<UserItems>>()
    private var listItems: ArrayList<UserItems> = ArrayList()

    fun setUser(username: String) {
        // Request API
        val apiKey = "a9baeefece821217ba18aa6860630dfd0f268f04"
        val url = "https://api.github.com/search/users?q=$username"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", apiKey)
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    //parsing json
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("items")
                    for (i in 0 until list.length()) {
                        val user = list.getJSONObject(i)
                        val userName: String = user.getString("login")
                        setDetailUser(userName)
                    }
                    listUsers.postValue(listItems)
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
                val result = String(responseBody)
                try {
                    //parsing json
                    val user = JSONObject(result)
                    val userItems = UserItems()
                    userItems.id = user.getInt("id")
                    userItems.username = user.getString("login")
                    userItems.foto = user.getString("avatar_url")
                    userItems.name = user.getString("name")
                    userItems.company = user.getString("company")
                    userItems.location = user.getString("location")
                    userItems.repository = user.getInt("public_repos")
                    userItems.followers = user.getInt("followers")
                    userItems.following = user.getInt("following")
                    listItems.add(userItems)
                    listUsers.postValue(listItems)
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

    fun getUsers(): LiveData<ArrayList<UserItems>> {
        return listUsers
    }
}