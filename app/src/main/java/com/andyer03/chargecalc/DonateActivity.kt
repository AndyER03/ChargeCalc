package com.andyer03.chargecalc

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_donate.*

class DonateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        IceCream.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yasobe.ru/na/AndyER03"))
            startActivity(i)
        }
        donateText.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yasobe.ru/na/AndyER03"))
            startActivity(i)
        }

        val sharedPreferencesDonateActivity = getSharedPreferences("Donate_Chest", Context.MODE_PRIVATE)
        IceCream.setOnLongClickListener {
            if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "0") {
                val counter = "1"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                IceCream.animate().translationZBy(100F).alpha(0F).duration = 0
                Toast.makeText(this, R.string.easteregg_where_is_the_ice_cream, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, R.string.easteregg_press_again, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "1") {
                val counter = "2"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                IceCream.animate().translationZBy(100F).alpha(100F).duration = 0
                Toast.makeText(this, R.string.easteregg_ice_cream_is_come_back, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "2") {
                val counter = "3"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                IceCream.animate().translationZBy(100F).alpha(0F).duration = 0
                Toast.makeText(this, R.string.easteregg_where_is_the_ice_cream, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, R.string.easteregg_press_again, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "3") {
                val counter = "4"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                IceCream.animate().translationZBy(100F).alpha(100F).duration = 0
                Toast.makeText(this, R.string.easteregg_ice_cream_is_come_back, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "4") {
                val counter = "5"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                IceCream.animate().translationZBy(100F).alpha(100F).duration = 0
                Toast.makeText(this, "=/", Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "5") {
                val counter = "6"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_stop, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "6") {
                val counter = "7"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_really_stop, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "7") {
                val counter = "8"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_offended, Toast.LENGTH_SHORT).show()
                finish()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "8") {
                val counter = "9"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, "...", Toast.LENGTH_SHORT).show()
                finish()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "9") {
                val counter = "10"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, "...", Toast.LENGTH_SHORT).show()
                finish()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "10") {
                val counter = "11"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_apologize, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "11") {
                val counter = "12"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_do_not_believe, Toast.LENGTH_SHORT).show()
                finish()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "12") {
                val counter = "13"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_do_not_believe, Toast.LENGTH_SHORT).show()
                finish()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "13") {
                val counter = "14"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_really_apologize, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "14") {
                val counter = "15"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_forgive, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "15") {
                val counter = "16"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_hahaha, Toast.LENGTH_SHORT).show()
                finish()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "16") {
                val counter = "0"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                Toast.makeText(this, R.string.easteregg_hahaha, Toast.LENGTH_SHORT).show()
                finish()
            }
            return@setOnLongClickListener true
        }

        donateText.setOnLongClickListener {
            if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "0") {
                Toast.makeText(this, R.string.easteregg_magic, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, R.string.easteregg_magic_part2, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0").toString() != "0") {
                val counter = "0"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                IceCream.animate().translationZBy(100F).alpha(100F).duration = 0
                Toast.makeText(this, R.string.easteregg_forgive_ontext, Toast.LENGTH_SHORT).show()
            }
            return@setOnLongClickListener true
        }
    }

    override fun onResume() {
        super.onResume()

        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        //deprecated in API 26
        when (sp.getString("bg_option", "1")) {
            "1" -> {
                DonateLayout.background = getDrawable(R.color.gray)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "2" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "3" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_2)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "4" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_3)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "5" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_4)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "6" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_5)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "7" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_6)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "8" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_7)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "9" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_8)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "10" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_9)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "11" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_10)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "12" -> {
                DonateLayout.background = getDrawable(R.color.black)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}
