package com.andyer03.chargecalc

import RunServiceOnBoot
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.*
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager
    private val channelId = "com.andyer03.chargecalc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val valuesChest = getSharedPreferences("Values_Chest", Context.MODE_PRIVATE)
        when {
            valuesChest.getString("counter", "0").toString() == "0" -> {
                saveBtn.text = getString(R.string.save_values_btn)
            }
            valuesChest.getString("counter", "0").toString() == "1" -> {
                saveBtn.text = getString(R.string.restore_values_btn)
            }
        }

        submit_button.setOnLongClickListener {
            advancedResult()
        }

        clearBtn.setOnClickListener {
            current_charge_value_input.requestFocus()
            allFieldsClear()
        }

        clearBtn.setOnLongClickListener {
            val editor = valuesChest.edit()
            editor.putInt(
                "curCharge",
                0
            )
            editor.putString(
                "timeLeft",
                0.toString().trim()
            )
            editor.putString(
                "remainingTime",
                0.toString().trim()
            )
            editor.apply()
            notification()
            Toast.makeText(this, R.string.notification_values_reset, Toast.LENGTH_SHORT).show()
            vibration()

            return@setOnLongClickListener true
        }
    }

    override fun onResume() {
        super.onResume()

        notification()

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val valuesChest = getSharedPreferences("Values_Chest", Context.MODE_PRIVATE)
        val startupIntent: Intent

        when (sp.getBoolean("bg_option", false)) {
            true -> {
                startupIntent = Intent(this@MainActivity, RunServiceOnBoot::class.java)
                startService(startupIntent)
            }
            false -> {
                startupIntent = Intent(this@MainActivity, MainActivity::class.java)
            }
        }


        saveBtn.setOnClickListener {
            if (valuesChest.getString("counter", "0").toString() == "0") {
                if ((current_charge_value_input.text.toString() == "") && (time_left_value_input.text.toString() == "")) {
                    current_charge_value_input.requestFocus()
                    return@setOnClickListener
                } else {
                    val counter = "1"

                    val editor = valuesChest.edit()
                    editor.putString(
                        "charge_value",
                        current_charge_value_input.text.toString().trim()
                    )
                    editor.putString(
                        "time_after_charge",
                        time_left_value_input.text.toString().trim()
                    )
                    editor.putString(
                        "live_time",
                        estimated_autonomy_days_number_input.text.toString().trim()
                    )
                    editor.putString("counter", counter)
                    editor.apply()
                    current_charge_value_input.requestFocus()

                    saveBtn.text = getString(R.string.restore_values_btn)
                }
            } else if (valuesChest.getString("counter", "0").toString() == "1") {
                current_charge_value_input.setText(
                    valuesChest.getString("charge_value", "").toString()
                )
                time_left_value_input.setText(
                    valuesChest.getString("time_after_charge", "").toString()
                )
                estimated_autonomy_days_number_input.setText(
                    valuesChest.getString("live_time", "").toString()
                )
                submit_button.text = getString(R.string.submit_button)

                current_charge_value_input.requestFocus()
            }
        }

        saveBtn.setOnLongClickListener {
            if (valuesChest.getString("counter", "0").toString() == "0") {
                Toast.makeText(this, R.string.toast_values_not_reset, Toast.LENGTH_SHORT).show()
            } else if (valuesChest.getString("counter", "0").toString() == "1") {
                val editor = valuesChest.edit()
                editor.putString("counter", "0")
                editor.apply()

                saveBtn.text = getString(R.string.save_values_btn)
                Toast.makeText(this, R.string.toast_values_reset, Toast.LENGTH_SHORT).show()

                vibration()
            }
            return@setOnLongClickListener true
        }

        when (sp.getString("bg_option", "1")) {
            "1" -> {
                MainLayout.background = getDrawable(R.color.gray)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "2" -> {
                MainLayout.background = getDrawable(R.drawable.gradient)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "3" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_2)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "4" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_3)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "5" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_4)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "6" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_5)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "7" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_6)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "8" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_7)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "9" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_8)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "10" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_9)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "11" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_10)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "12" -> {
                MainLayout.background = getDrawable(R.color.black)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        when (sp.getBoolean("estimated_autonomy_days_switch", false)) {
            true -> {
                estimated_autonomy_days_number_header.visibility = View.VISIBLE
                estimated_autonomy_days_number_input.visibility = View.VISIBLE
            }
            false -> {
                estimated_autonomy_days_number_header.visibility = View.GONE
                estimated_autonomy_days_number_input.visibility = View.GONE
                liveTimeFieldClear()
            }
        }

        when (sp.getBoolean("clear_btn_switch", false)) {
            true -> {
                clearBtn.visibility = View.VISIBLE
            }
            false -> {
                clearBtn.visibility = View.GONE
            }
        }

        when (sp.getBoolean("notification_switch", false)) {
            true -> {
                return
            }
            false -> {
                notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancelAll()
            }
        }

        when (valuesChest.getString("counter", "0").toString()) {
            "0" -> {
                saveBtn.text = getString(R.string.save_values_btn)
            }
            "1" -> {
                saveBtn.text = getString(R.string.restore_values_btn)
            }
        }
    }

    private fun vibration() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val vibrationSwitch = sp.getBoolean("vibration_switch", true)
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (vibrationSwitch) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(
                    VibrationEffect.createOneShot(
                        100,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                //deprecated in API 26
                v.vibrate(100)
            }
        }
    }

    fun restoreButton(view: View) {
        submit_button.text = getString(R.string.submit_button)
    }

    private fun notification() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val valuesChest = getSharedPreferences("Values_Chest", Context.MODE_PRIVATE)
        val curCharge = valuesChest.getInt("curCharge", 0)
        val timeLeft = valuesChest.getString("timeLeft", "0").toString()
        val remainingInt = valuesChest.getString("remainingTime", "0").toString()
        var color = 0
        when (sp.getString("bg_option", "1")) {
            "2" -> {
                color = 0xFF5C3F9F.toInt()
            }
            "3" -> {
                color = 0xFFA81A84.toInt()
            }
            "4" -> {
                color = 0xFF009FB3.toInt()
            }
            "5" -> {
                color = 0xFFE69152.toInt()
            }
            "6" -> {
                color = 0xFFFF9800.toInt()
            }
            "7" -> {
                color = 0xFF5F981C.toInt()
            }
            "8" -> {
                color = 0xFF9C27B0.toInt()
            }
            "9" -> {
                color = 0xFF673AB7.toInt()
            }
            "10" -> {
                color = 0xFF0092A5.toInt()
            }
            "11" -> {
                color = 0xFF00A7D1.toInt()
            }
            "12" -> {
                color = 0xFF000000.toInt()
            }
        }

        when (sp.getBoolean("notification_switch", false)) {
            true -> {
                val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    val name = getString(R.string.result_channel_name)
                    val importance = NotificationManager.IMPORTANCE_MIN
                    val mChannel = NotificationChannel(channelId, name, importance)
                    mChannel.description = getString(R.string.result_channel_description)
                    val notificationManager =
                        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.createNotificationChannel(mChannel)

                    val notificationBuilder = NotificationCompat.Builder(this, name)
                        .setChannelId(channelId)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                resources,
                                R.mipmap.ic_launcher
                            )
                        )
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(getString(R.string.should_enough_time_with_current_charge_notification) + " $remainingInt")
                        .setContentText("$curCharge%")
                        .setProgress(100, curCharge, false)
                        .setSubText(getString(R.string.time_left_value) + " $timeLeft")
                        .setOngoing(true)
                        .setColor(color)
                        .setShowWhen(false)
                        .setPriority(NotificationCompat.PRIORITY_MIN)
                        .setContentIntent(pendingIntent)
                    notificationManager.notify(1, notificationBuilder.build())
                } else {
                    val notificationBuilder = NotificationCompat.Builder(this)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                resources,
                                R.mipmap.ic_launcher
                            )
                        )
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(getString(R.string.should_enough_time_with_current_charge_notification) + " $remainingInt")
                        .setContentText("$curCharge%")
                        .setColor(Color.GREEN)
                        .setProgress(100, curCharge, false)
                        .setSubText(getString(R.string.time_left_value) + " $timeLeft")
                        .setOngoing(true)
                        .setColor(color)
                        .setShowWhen(false)
                        .setPriority(NotificationCompat.PRIORITY_MIN)
                        .setContentIntent(pendingIntent)
                    notificationManager.notify(1, notificationBuilder.build())
                }
            }
            false -> {
                notificationManager.cancelAll()
            }
        }
    }

    private fun allFieldsClear() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        when (sp.getBoolean("alternative_clear_switch", false)) {
            true -> {
                current_charge_value_input.text = null
                time_left_value_input.text = null
                estimated_autonomy_days_number_input.text = null
            }
            false -> {
                current_charge_value_input.text.clear()
                time_left_value_input.text.clear()
                estimated_autonomy_days_number_input.text.clear()
            }
        }
        current_charge_value_input.requestFocus()
    }

    private fun chargeFieldClear() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        when (sp.getBoolean("alternative_clear_switch", false)) {
            true -> {
                current_charge_value_input.text = null
            }
            false -> {
                current_charge_value_input.text.clear()
            }
        }
        current_charge_value_input.requestFocus()
    }

    private fun timeFieldClear() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        when (sp.getBoolean("alternative_clear_switch", false)) {
            true -> {
                time_left_value_input.text = null
            }
            false -> {
                time_left_value_input.text.clear()
            }
        }
        current_charge_value_input.requestFocus()
    }

    private fun liveTimeFieldClear() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        when (sp.getBoolean("alternative_clear_switch", false)) {
            true -> {
                estimated_autonomy_days_number_input.text = null
            }
            false -> {
                estimated_autonomy_days_number_input.text.clear()
            }
        }
        current_charge_value_input.requestFocus()
    }

    private fun emptyFieldsCheck(): Boolean {
        if ((current_charge_value_input.text.toString() == "") && (time_left_value_input.text.toString() == "")) {
            current_charge_value_input.requestFocus()
            current_charge_value_input.error = getString(R.string.warning_input_values)
            time_left_value_input.error = getString(R.string.warning_input_values)
            return false
        } else if (current_charge_value_input.text.toString() == "") {
            current_charge_value_input.requestFocus()
            current_charge_value_input.error = getString(R.string.warning_input_values)
            return false
        } else if (time_left_value_input.text.toString() == "") {
            time_left_value_input.requestFocus()
            time_left_value_input.error = getString(R.string.warning_input_values)
            return false
        } else if (((current_charge_value_input.text.toString()
                .toInt() >= 100) || (current_charge_value_input.text.toString()
                .toInt() == 0)) && (time_left_value_input.text.toString().toInt() == 0)
        ) {
            current_charge_value_input.error =
                getString(R.string.warning_calculation_input_other_value)
            time_left_value_input.error =
                getString(R.string.warning_calculation_input_other_value)
            allFieldsClear()
            return false
        } else if ((current_charge_value_input.text.toString()
                .toInt() == 0) || (current_charge_value_input.text.toString().toInt() >= 100)
        ) {
            current_charge_value_input.error =
                getString(R.string.warning_calculation_input_other_value)
            chargeFieldClear()
            return false
        } else if ((time_left_value_input.text.toString().toInt() == 0)) {
            time_left_value_input.error =
                getString(R.string.warning_calculation_input_other_value)
            timeFieldClear()
            return false
        }
        return true
    }

    fun clickSubmit(view: View) {

        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        when (sp.getBoolean("always_advanced_result", false)) {
            true -> {
                restoreButton(view)
                advancedResult()
            }
            false -> {
                restoreButton(view)

                emptyFieldsCheck()
                when (emptyFieldsCheck()) {
                    true -> {
                        val curCharge = current_charge_value_input.text.toString().toFloat()
                        val timeAfterCharge = time_left_value_input.text.toString().toFloat()
                        val liveTime = estimated_autonomy_days_number_input.text.toString()
                        val percentsLeft: Float = 100 - curCharge
                        val ratio: Float = percentsLeft / timeAfterCharge
                        val shouldEnough: Float = 100 / ratio
                        val remaining: Float = shouldEnough - timeAfterCharge
                        val remainingInt: Int = remaining.toInt()

                        if (remaining < 0) {
                            submit_button.text = getString(R.string.warning_error_calculation)
                            Handler().postDelayed({
                                restoreButton(view = view)
                            }, 3000)
                            return
                        } else {
                            val lastDigit: Int = remainingInt % 10
                            val penultimateDigitCalc = (remainingInt - lastDigit) / 10
                            val penultimateDigit: Int = penultimateDigitCalc % 10

                            if (estimated_autonomy_days_number_input.text.toString() == "") {
                                if (penultimateDigit == 1) {
                                    val submitButtonText =
                                        "$remainingInt " + getString(R.string.simple_result_many_time)
                                    submit_button.text = submitButtonText
                                    return
                                }
                                if (lastDigit == 1) {
                                    val submitButtonText =
                                        "$remainingInt " + getString(R.string.simple_result_one_time)
                                    submit_button.text = submitButtonText
                                } else if ((lastDigit == 2) || (lastDigit == 3) || (lastDigit == 4)) {
                                    val submitButtonText =
                                        "$remainingInt " + getString(R.string.simple_result_some_time)
                                    submit_button.text = submitButtonText
                                } else {
                                    val submitButtonText =
                                        "$remainingInt " + getString(R.string.simple_result_many_time)
                                    submit_button.text = submitButtonText
                                }
                            } else {
                                val remaining2: Float =
                                    ((remaining / liveTime.toFloat()) * (shouldEnough - remaining)) + (liveTime.toFloat() / 2)
                                val remainingInt2: Int = remaining2.toInt()

                                val lastDigit2var1: Int = remainingInt2 % 10
                                val penultimateDigitCalc2var1 =
                                    (remainingInt2 - lastDigit2var1) / 10
                                val penultimateDigit2var1: Int = penultimateDigitCalc2var1 % 10

                                if (penultimateDigit2var1 == 1) {
                                    val submitButtonText =
                                        "$remainingInt ~ $remainingInt2 " + getString(R.string.simple_result_many_time)
                                    submit_button.text = submitButtonText
                                    return
                                }
                                if (lastDigit2var1 == 1) {
                                    val submitButtonText =
                                        "$remainingInt ~ $remainingInt2 " + getString(R.string.simple_result_one_time)
                                    submit_button.text = submitButtonText
                                } else if ((lastDigit2var1 == 2) || (lastDigit2var1 == 3) || (lastDigit2var1 == 4)) {
                                    val submitButtonText =
                                        "$remainingInt ~ $remainingInt2 " + getString(R.string.simple_result_some_time)
                                    submit_button.text = submitButtonText
                                } else {
                                    val submitButtonText =
                                        "$remainingInt ~ $remainingInt2 " + getString(R.string.simple_result_many_time)
                                    submit_button.text = submitButtonText
                                }
                            }

                            val valuesChest =
                                getSharedPreferences("Values_Chest", Context.MODE_PRIVATE)
                            val editor = valuesChest.edit()
                            editor.putInt(
                                "curCharge",
                                curCharge.toInt()
                            )
                            editor.putString(
                                "timeLeft",
                                timeAfterCharge.toInt().toString().trim()
                            )
                            editor.putString(
                                "remainingTime",
                                submit_button.text.toString().trim()
                            )
                            editor.apply()

                            notification()
                        }
                    }
                    false -> {
                        return
                    }
                }
            }
        }
    }

    private fun advancedResult(): Boolean {
        if ((current_charge_value_input.text.toString() == "236") && (time_left_value_input.text.toString() == "1")) {
            current_charge_value_input.text.clear()
            time_left_value_input.text.clear()
            val intent = Intent(this, DonateActivity::class.java)
            startActivity(intent)
            return true
        } else if ((current_charge_value_input.text.toString() == "236") && (time_left_value_input.text.toString() == "2")) {
            current_charge_value_input.text.clear()
            time_left_value_input.text.clear()
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        } else if ((current_charge_value_input.text.toString() == "236") && (time_left_value_input.text.toString() == "3")) {
            current_charge_value_input.text.clear()
            time_left_value_input.text.clear()
            aboutDialog()
            return true
        } else if ((current_charge_value_input.text.toString() == "236") && (time_left_value_input.text.toString() == "4")) {
            current_charge_value_input.text.clear()
            time_left_value_input.text.clear()
            exitDialog()
            return true
        }
        when (emptyFieldsCheck()) {
            true -> {
                // Calculating
                val curCharge = current_charge_value_input.text.toString().toFloat()
                val timeAfterCharge = time_left_value_input.text.toString().toFloat()
                val liveTime = estimated_autonomy_days_number_input.text.toString()
                val percentsLeft: Float = 100 - curCharge
                val ratio: Float = percentsLeft / timeAfterCharge
                val shouldEnough: Float = 100 / ratio
                val remaining: Float = shouldEnough - timeAfterCharge
                val remainingInt: Int = remaining.toInt()

                if (estimated_autonomy_days_number_input.text.toString() == "") {
                    val lastDigit: Int = remainingInt % 10
                    val penultimateDigitCalc = (remainingInt - lastDigit) / 10
                    val penultimateDigit: Int = penultimateDigitCalc % 10

                    if (penultimateDigit == 1) {
                        val submitButtonText =
                            "$remainingInt " + getString(R.string.simple_result_many_time)
                        submit_button.text = submitButtonText
                        return true
                    }
                    if (lastDigit == 1) {
                        val submitButtonText =
                            "$remainingInt " + getString(R.string.simple_result_one_time)
                        submit_button.text = submitButtonText
                    } else if ((lastDigit == 2) || (lastDigit == 3) || (lastDigit == 4)) {
                        val submitButtonText =
                            "$remainingInt " + getString(R.string.simple_result_some_time)
                        submit_button.text = submitButtonText
                    } else {
                        val submitButtonText =
                            "$remainingInt " + getString(R.string.simple_result_many_time)
                        submit_button.text = submitButtonText
                    }

                    val builder = AlertDialog.Builder(this)
                        .setTitle(R.string.advanced_result_title)
                        .setMessage(
                            getString(R.string.current_charge_value) + " ${curCharge.toInt()}%" + "\n" +
                                    getString(R.string.battery_charge_left) + " ${percentsLeft.toInt()}%" + "\n" +
                                    getString(R.string.battery_percent_discharge_at_time) + " ${ratio.toInt()}%" + "\n\n" +
                                    getString(R.string.time_left_value) + " ${timeAfterCharge.toInt()}" + "\n" +
                                    getString(R.string.should_enough_time_with_full_charge) + " ${shouldEnough.toInt()}" + "\n" +
                                    getString(R.string.should_enough_time_with_current_charge) + " $remainingInt"
                        )
                    builder.show().toString().toBoolean()
                } else {
                    val remaining2: Float =
                        ((remaining / liveTime.toFloat()) * (shouldEnough - remaining)) + (liveTime.toFloat() / 2)
                    val remainingInt2: Int = remaining2.toInt()

                    val lastDigit2var1: Int = remainingInt2 % 10
                    val penultimateDigitCalc2var1 =
                        (remainingInt2 - lastDigit2var1) / 10
                    val penultimateDigit2var1: Int = penultimateDigitCalc2var1 % 10

                    if (penultimateDigit2var1 == 1) {
                        val submitButtonText =
                            "$remainingInt ~ $remainingInt2 " + getString(R.string.simple_result_many_time)
                        submit_button.text = submitButtonText
                        return true
                    }
                    if (lastDigit2var1 == 1) {
                        val submitButtonText =
                            "$remainingInt ~ $remainingInt2 " + getString(R.string.simple_result_one_time)
                        submit_button.text = submitButtonText
                    } else if ((lastDigit2var1 == 2) || (lastDigit2var1 == 3) || (lastDigit2var1 == 4)) {
                        val submitButtonText =
                            "$remainingInt ~ $remainingInt2 " + getString(R.string.simple_result_some_time)
                        submit_button.text = submitButtonText
                    } else {
                        val submitButtonText =
                            "$remainingInt ~ $remainingInt2 " + getString(R.string.simple_result_many_time)
                        submit_button.text = submitButtonText
                    }

                    val builder = AlertDialog.Builder(this)
                        .setTitle(R.string.advanced_result_title)
                        .setMessage(
                            getString(R.string.current_charge_value) + " ${curCharge.toInt()}%" + "\n" +
                                    getString(R.string.battery_charge_left) + " ${percentsLeft.toInt()}%" + "\n" +
                                    getString(R.string.battery_percent_discharge_at_time) + " ${ratio.toInt()}%" + "\n\n" +
                                    getString(R.string.time_left_value) + " ${timeAfterCharge.toInt()}" + "\n" +
                                    getString(R.string.should_enough_time_with_full_charge) + " ${shouldEnough.toInt()}" + "\n" +
                                    getString(R.string.should_enough_time_with_current_charge) + " $remainingInt ~ $remainingInt2"
                        )
                    builder.show().toString().toBoolean()
                }

                val valuesChest =
                    getSharedPreferences("Values_Chest", Context.MODE_PRIVATE)
                val editor = valuesChest.edit()
                editor.putInt(
                    "curCharge",
                    curCharge.toInt()
                )
                editor.putString(
                    "timeLeft",
                    timeAfterCharge.toInt().toString().trim()
                )
                editor.putString(
                    "remainingTime",
                    submit_button.text.toString().trim()
                )
                editor.apply()

                notification()
            }
            false -> {
                return false
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.donate -> {
                val intent = Intent(this, DonateActivity::class.java)
                startActivity(intent)
            }
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.about -> {
                aboutDialog()
            }
            R.id.exit -> {
                exitDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun aboutDialog() {
        val builder = AlertDialog.Builder(this)
            .setTitle(R.string.about_title)
            .setMessage(
                getString(R.string.version_title) + " " + BuildConfig.VERSION_NAME + "\n" +
                        getString(R.string.created_by) + "\n\n" +
                        getString(R.string.support_in_developing) + "\n" +
                        getString(R.string.who_support_in_developing)
            )
        builder.show()
    }

    private fun exitDialog() {
        val builder = AlertDialog.Builder(this)
            .setTitle(R.string.exit_title)
            .setMessage(getString(R.string.exit_dialog))
        builder.setPositiveButton(R.string.exit_title) { _: DialogInterface?, _: Int ->
            finish()
        }
        builder.setNegativeButton(R.string.no_button) { _: DialogInterface?, _: Int ->
            DialogInterface.BUTTON_NEGATIVE
        }
        builder.show()
    }

    override fun onBackPressed() {
        exitDialog()
    }
}

