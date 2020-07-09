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
                    "Default" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient)
                    }
                    "Blue" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient)
                    }
                    "Pink" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_2)
                    }
                    "Cyan" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_3)
                    }
                    "Peach" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_4)
                    }
                    "Orange" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_5)
                    }
                    "Lime" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_6)
                    }
                    "Sherbet" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_7)
                    }
                    "Versus" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_8)
                    }
                    "Rainbow" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_9)
                    }
                    "Breeze" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_10)
                    }
                    "Apple" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_11)
                    }
                    "Purple" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_12)
                    }
                    "Plum" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_13)
                    }
                    "Black" -> {
                        SplashActivity.background = getDrawable(R.color.black)
                    }
                }
            }
            false -> {
                when (sp.getString("bg_option", "1")) {
                    "Default" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient)
                    }
                    "Blue" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient)
                    }
                    "Pink" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_2)
                    }
                    "Cyan" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_3)
                    }
                    "Peach" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_4)
                    }
                    "Orange" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_5)
                    }
                    "Lime" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_6)
                    }
                    "Sherbet" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_7)
                    }
                    "Versus" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_8)
                    }
                    "Rainbow" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_9)
                    }
                    "Breeze" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_10)
                    }
                    "Apple" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_11)
                    }
                    "Purple" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_12)
                    }
                    "Plum" -> {
                        SplashActivity.background = getDrawable(R.drawable.gradient_13)
                    }
                    "Black" -> {
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
