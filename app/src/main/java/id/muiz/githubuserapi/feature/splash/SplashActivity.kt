package id.muiz.githubuserapi.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.core.base.BaseActivity
import id.muiz.githubuserapi.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadAnimation()
        startIntent()
    }

    private fun loadAnimation() {
        val fromBottom = AnimationUtils.loadAnimation(
            this,
            R.anim.frombottom
        )

        splashText.startAnimation(fromBottom)
        nameDev.startAnimation(fromBottom)
    }

    private fun startIntent() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}
