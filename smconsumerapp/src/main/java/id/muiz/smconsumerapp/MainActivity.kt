package id.muiz.smconsumerapp

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.muiz.smconsumerapp.utility.toListUserFavorite
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchDataFromContentProvider()
    }

    private fun fetchDataFromContentProvider() {
        val table = "favorit_table"
        val authority = "id.muiz.githubuserapi.provider"

        val uri : Uri = Uri.Builder()
            .scheme("content")
            .authority(authority)
            .appendPath(table)
            .build()

        val contentResolver = this.contentResolver
        val cursor = contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )

        if (cursor != null) {
            initAdapter(cursor)
        } else {
            initAdapter(cursor)
        }
    }

    private fun initAdapter(cursor: Cursor?) {
        rv_user?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        cursor?.let {
            mainAdapter.setItems(it.toListUserFavorite())
        }

    }

}
