package com.andyer03.chargecalc

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        //deprecated in API 26
        when (sp.getString("bg_option", "1")) {
            "1" -> {
                SettingsLayout.background = getDrawable(R.color.gray)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "2" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "3" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_2)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "4" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_3)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "5" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_4)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "6" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_5)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "7" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_6)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "8" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_7)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "9" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_8)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "10" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_9)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "11" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_10)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "12" -> {
                SettingsLayout.background = getDrawable(R.color.black)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

}