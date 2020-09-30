package id.muiz.githubuserapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import id.muiz.githubuserapi.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val fromBottom = AnimationUtils.loadAnimation(this, R.anim.frombottom)

        splashText.startAnimation(fromBottom)
        nameDev.startAnimation(fromBottom)

        Handler().postDelayed({
            val moveMain = Intent(this, MainActivity::class.java)
            startActivity(moveMain)
            finish()
        }, 3000)
    }
}
