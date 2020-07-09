package com.andyer03.chargecalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.*
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
        when (sp.getString("bg_option", "Default")) {
            "Default" -> {
                SettingsLayout.background = getDrawable(R.color.gray)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "Blue" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Pink" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_2)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Cyan" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_3)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Peach" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_4)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Orange" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_5)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Lime" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_6)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Sherbet" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_7)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Versus" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_8)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Rainbow" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_9)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Breeze" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_10)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Apple" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_11)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Purple" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_12)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Plum" -> {
                SettingsLayout.background = getDrawable(R.drawable.gradient_13)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Black" -> {
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

            findPreference<Preference>("about_preference")?.title =
                getString(R.string.app_name) + " " + "v" + BuildConfig.VERSION_NAME
            findPreference<Preference>("about_preference")?.summary =
                getString(R.string.created_by) + "\n\n" +
                        getString(R.string.support_in_developing) + "\n" +
                        getString(R.string.who_support_in_developing)
        }
    }
}