package id.muiz.githubuserapi.feature.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.muiz.githubuserapi.R

class PengaturanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengaturan)
        initApp()
    }

    private fun initApp() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.settings, PengaturanFragment())
            .commit()

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.settings)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
