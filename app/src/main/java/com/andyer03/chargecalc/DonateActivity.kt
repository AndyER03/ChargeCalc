package com.andyer03.chargecalc

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_donate.*

class DonateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        IceCream.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yasobe.ru/na/AndyER03"))
            startActivity(i)
        }
        donateText.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yasobe.ru/na/AndyER03"))
            startActivity(i)
        }

        val sharedPreferencesDonateActivity =
            getSharedPreferences("Donate_Chest", Context.MODE_PRIVATE)
        IceCream.setOnLongClickListener {
            when {
                sharedPreferencesDonateActivity.getString("counter", "0").toString() == "0" -> {
                    val counter = "1"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    IceCream.animate().translationZBy(100F).alpha(0F).duration = 0
                    Toast.makeText(this, R.string.easter_egg_where_is_the_ice_cream, Toast.LENGTH_SHORT)
                        .show()
                    Toast.makeText(this, R.string.easter_egg_press_again, Toast.LENGTH_SHORT).show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "1"
                -> {
                    val counter = "2"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    IceCream.animate().translationZBy(100F).alpha(100F).duration = 0
                    Toast.makeText(this, R.string.easter_egg_ice_cream_is_come_back, Toast.LENGTH_SHORT)
                        .show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "2"
                -> {
                    val counter = "3"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    IceCream.animate().translationZBy(100F).alpha(0F).duration = 0
                    Toast.makeText(this, R.string.easter_egg_where_is_the_ice_cream, Toast.LENGTH_SHORT)
                        .show()
                    Toast.makeText(this, R.string.easter_egg_press_again, Toast.LENGTH_SHORT).show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "3"
                -> {
                    val counter = "4"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    IceCream.animate().translationZBy(100F).alpha(100F).duration = 0
                    Toast.makeText(this, R.string.easter_egg_ice_cream_is_come_back, Toast.LENGTH_SHORT)
                        .show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "4"
                -> {
                    val counter = "5"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    IceCream.animate().translationZBy(100F).alpha(100F).duration = 0
                    Toast.makeText(this, "=/", Toast.LENGTH_SHORT).show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "5"
                -> {
                    val counter = "6"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_stop, Toast.LENGTH_SHORT).show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "6"
                -> {
                    val counter = "7"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_really_stop, Toast.LENGTH_SHORT).show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "7"
                -> {
                    val counter = "8"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_offended, Toast.LENGTH_SHORT).show()
                    finish()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "8"
                -> {
                    val counter = "9"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, "...", Toast.LENGTH_SHORT).show()
                    finish()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "9"
                -> {
                    val counter = "10"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, "...", Toast.LENGTH_SHORT).show()
                    finish()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "10"
                -> {
                    val counter = "11"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_apologize, Toast.LENGTH_SHORT).show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "11"
                -> {
                    val counter = "12"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_do_not_believe, Toast.LENGTH_SHORT).show()
                    finish()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "12"
                -> {
                    val counter = "13"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_do_not_believe, Toast.LENGTH_SHORT).show()
                    finish()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "13"
                -> {
                    val counter = "14"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_really_apologize, Toast.LENGTH_SHORT).show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "14"
                -> {
                    val counter = "15"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_forgive, Toast.LENGTH_SHORT).show()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "15"
                -> {
                    val counter = "16"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_ha_ha_ha, Toast.LENGTH_SHORT).show()
                    finish()
                }
                sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() == "16"
                -> {
                    val counter = "0"
                    val editor = sharedPreferencesDonateActivity.edit()
                    editor.putString("counter", counter)
                    editor.apply()
                    Toast.makeText(this, R.string.easter_egg_ha_ha_ha, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            return@setOnLongClickListener true
        }

        donateText.setOnLongClickListener {
            if (sharedPreferencesDonateActivity.getString("counter", "0").toString() == "0") {
                Toast.makeText(this, R.string.easter_egg_magic, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, R.string.easter_egg_magic_part2, Toast.LENGTH_SHORT).show()
            } else if (sharedPreferencesDonateActivity.getString("counter", "0")
                    .toString() != "0"
            ) {
                val counter = "0"
                val editor = sharedPreferencesDonateActivity.edit()
                editor.putString("counter", counter)
                editor.apply()
                IceCream.animate().translationZBy(100F).alpha(100F).duration = 0
                Toast.makeText(this, R.string.easter_egg_forgive_on_text, Toast.LENGTH_SHORT).show()
            }
            return@setOnLongClickListener true
        }
    }

    override fun onResume() {
        super.onResume()

        val preferenceScreen = PreferenceManager.getDefaultSharedPreferences(this)

        //deprecated in API 26
        when (preferenceScreen.getString("bg_option", "Default")) {
            "Default" -> {
                DonateLayout.background = getDrawable(R.color.gray)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "Blue" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Pink" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_2)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Cyan" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_3)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Peach" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_4)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Orange" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_5)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Lime" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_6)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Sherbet" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_7)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Versus" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_8)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Rainbow" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_9)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Breeze" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_10)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Apple" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_11)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Plum" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_13)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Purple" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_12)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Shine" -> {
                DonateLayout.background = getDrawable(R.drawable.gradient_14)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Black" -> {
                DonateLayout.background = getDrawable(R.color.black)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
