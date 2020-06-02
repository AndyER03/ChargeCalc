package com.andyer03.chargecalc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        appVer.text = BuildConfig.VERSION_NAME

        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        when (sp.getBoolean("splash_bg_switch", false)) {

            true -> {
                when (sp.getString("splash_bg_option", "1")) {
                    "1" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient)
                    }
                    "2" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_2)
                    }
                    "3" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_3)
                    }
                    "4" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_4)
                    }
                    "5" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_5)
                    }
                    "6" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_6)
                    }
                    "7" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_7)
                    }
                    "8" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_8)
                    }
                    "9" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_9)
                    }
                    "10" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_10)
                    }
                    "11" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient)
                    }
                    "12" -> {
                        SplashActivity.background = getDrawable(R.color.black)
                    }
                }
            }
            false -> {
                when (sp.getString("bg_option", "1")) {
                    "1" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient)
                    }
                    "2" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient)
                    }
                    "3" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_2)
                    }
                    "4" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_3)
                    }
                    "5" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_4)
                    }
                    "6" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_5)
                    }
                    "7" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_6)
                    }
                    "8" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_7)
                    }
                    "9" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_8)
                    }
                    "10" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_9)
                    }
                    "11" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_10)
                    }
                    "12" -> {
                        SplashActivity.background = getDrawable(R.color.black)
                    }
                }
            }
        }

        when (sp.getBoolean("splash_option", true)) {
            false -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            true -> {
                Handler().postDelayed({
                    createdBy.animate().alphaBy(100F).alpha(0F).duration = 1000
                }, 2000)

                Handler().postDelayed({
                    createdBy.visibility = View.GONE

                    icon.animate().alphaBy(100F).alpha(0F).duration = 1000
                    appName.animate().alphaBy(100F).alpha(0F).duration = 1000
                    appVer.animate().alphaBy(100F).alpha(0F).duration = 1000
                }, 3000)

                Handler().postDelayed({
                    icon.visibility = View.GONE
                    appName.visibility = View.GONE

                    SplashActivity.background = getDrawable(R.color.black)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 4000)
            }
        }
    }
}
