package com.andyer03.chargecalc

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.*
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.FLAG_SHOW_LIGHTS
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
    }

    override fun onResume() {
        super.onResume()

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val valuesChest = getSharedPreferences("Values_Chest", Context.MODE_PRIVATE)

        submit_button.setOnLongClickListener {
            when (sp.getBoolean("restore_btn_notification_switch", false)) {
                true -> {
                    val curChargeBackup = valuesChest.getInt("curChargeBackup", 0)
                    val timeLeftBackup = valuesChest.getString("timeLeftBackup", "0").toString()
                    val remainingIntBackup =
                        valuesChest.getString("remainingTimeBackup", "0").toString()

                    val editor = valuesChest.edit()
                    editor.putInt(
                        "curCharge",
                        curChargeBackup
                    )
                    editor.putString(
                        "timeLeft",
                        timeLeftBackup.trim()
                    )
                    editor.putString(
                        "remainingTime",
                        remainingIntBackup.trim()
                    )
                    editor.apply()

                    notification()
                }
                false -> {
                    advancedResult()
                }
            }
            btnVibration()
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
            }
            btnVibration()
            return@setOnLongClickListener true
        }

        clearBtn.setOnClickListener {
            if ((current_charge_value_input.text.toString() != "") || (time_left_value_input.text.toString() != "") || (estimated_autonomy_days_number_input.text.toString() != "")) {
                current_charge_value_input.requestFocus()
                allFieldsClear()
            }
            else {
                Toast.makeText(this, R.string.toast_values_not_reset, Toast.LENGTH_SHORT).show()
            }
        }

        clearBtn.setOnLongClickListener {
            when (sp.getBoolean("clear_btn_notification_switch", false)) {
                true -> {
                    when (sp.getBoolean("notification_switch", false)) {
                        true -> {
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
                            Toast.makeText(
                                this,
                                R.string.notification_values_reset,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        false -> {
                            Toast.makeText(this, R.string.turn_on_notifications, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                false -> {
                    if ((current_charge_value_input.text.toString() != "") || (time_left_value_input.text.toString() != "")) {
                        current_charge_value_input.requestFocus()
                        allFieldsClear()
                    }
                    else {
                        Toast.makeText(this, R.string.toast_values_not_reset, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            btnVibration()
            return@setOnLongClickListener true
        }

        when (sp.getString("bg_option", "Default")) {
            "Default" -> {
                MainLayout.background = getDrawable(R.color.gray)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "Blue" -> {
                MainLayout.background = getDrawable(R.drawable.gradient)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Pink" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_2)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Cyan" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_3)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Peach" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_4)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Orange" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_5)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Lime" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_6)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Sherbet" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_7)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Versus" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_8)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Rainbow" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_9)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Breeze" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_10)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Apple" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_11)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Plum" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_13)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Purple" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_12)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Shine" -> {
                MainLayout.background = getDrawable(R.drawable.gradient_14)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "Black" -> {
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

    private fun btnVibration(): Boolean {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val vibrationSwitch = sp.getBoolean("vibration_btn_switch", true)
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
        return true
    }

    private fun notificationVibration() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val vibrationSwitch = sp.getBoolean("vibration_notification_switch", true)
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

    @SuppressLint("InlinedApi")
    private fun notification(): Boolean {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val valuesChest = getSharedPreferences("Values_Chest", Context.MODE_PRIVATE)
        val curCharge = valuesChest.getInt("curCharge", 0)
        val timeLeft = valuesChest.getString("timeLeft", "0").toString()
        val remainingInt = valuesChest.getString("remainingTime", "0").toString()
        val hideNullNotification = sp.getBoolean("null_notification_switch", false)

        if ((hideNullNotification) && ((curCharge == 0) && (timeLeft == "0"))) {
            notificationManager.cancelAll()
        } else {
            var notificationColor = 0
            var notificationLedColor = 0
            when (sp.getString("bg_option", "Default")) {
                "Blue" -> {
                    notificationColor = 0xFF5C3F9F.toInt()
                }
                "Pink" -> {
                    notificationColor = 0xFFA81A84.toInt()
                }
                "Cyan" -> {
                    notificationColor = 0xFF009FB3.toInt()
                }
                "Peach" -> {
                    notificationColor = 0xFFE69152.toInt()
                }
                "Orange" -> {
                    notificationColor = 0xFFFF9800.toInt()
                }
                "Lime" -> {
                    notificationColor = 0xFF5F981C.toInt()
                }
                "Sherbet" -> {
                    notificationColor = 0xFF7769FF.toInt()
                }
                "Versus" -> {
                    notificationColor = 0xFF673AB7.toInt()
                }
                "Rainbow" -> {
                    notificationColor = 0xFF0092A5.toInt()
                }
                "Breeze" -> {
                    notificationColor = 0xFF00A7D1.toInt()
                }
                "Apple" -> {
                    notificationColor = 0xFFBB0000.toInt()
                }
                "Plum" -> {
                    notificationColor = 0xFF9C27B0.toInt()
                }
                "Purple" -> {
                    notificationColor = 0xFF9C27B0.toInt()
                }
                "Shine" -> {
                    notificationColor = 0xFF00A7D1.toInt()
                }
                "Black" -> {
                    notificationColor = 0xFF000000.toInt()
                }
            }

            when (sp.getBoolean("led_notification_switch", true)) {
                true -> {
                    when (sp.getString("led_notification_color_option", "White")) {
                        "White" -> {
                            notificationLedColor = 0xFFFFFFFF.toInt()
                        }
                        "Red" -> {
                            notificationLedColor = 0xFFFF0000.toInt()
                        }
                        "Magenta" -> {
                            notificationLedColor = 0xFFFF00FF.toInt()
                        }
                        "Yellow" -> {
                            notificationLedColor = 0xFF00FFFF.toInt()
                        }
                        "Cyan" -> {
                            notificationLedColor = 0xFF00FFFF.toInt()
                        }
                        "Green" -> {
                            notificationLedColor = 0xFF00FF00.toInt()
                        }
                        "Blue" -> {
                            notificationLedColor = 0xFF0000FF.toInt()
                        }
                    }
                }
                false -> {
                    notificationLedColor = 0
                }
            }

            val notificationOngoing = when (sp.getBoolean("ongoing_notification_switch", false)) {
                true -> {
                    true
                }
                false -> {
                    false
                }
            }

            val notificationSound = when (sp.getBoolean("silent_notification_switch", true)) {
                true -> {
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                }
                false -> {
                    null
                }
            }

            var notificationPriority = NotificationCompat.PRIORITY_DEFAULT
            var notificationImportance = NotificationManager.IMPORTANCE_DEFAULT
            when (sp.getString("notification_priority_option", "Default")) {
                "MAX" -> {
                    notificationPriority = NotificationCompat.PRIORITY_MAX
                    notificationImportance = NotificationManager.IMPORTANCE_MAX
                }
                "HIGH" -> {
                    notificationPriority = NotificationCompat.PRIORITY_HIGH
                    notificationImportance = NotificationManager.IMPORTANCE_HIGH
                }
                "Default" -> {
                    notificationPriority = NotificationCompat.PRIORITY_DEFAULT
                    notificationImportance = NotificationManager.IMPORTANCE_DEFAULT
                }
                "LOW" -> {
                    notificationPriority = NotificationCompat.PRIORITY_LOW
                    notificationImportance = NotificationManager.IMPORTANCE_LOW
                }
                "MIN" -> {
                    notificationPriority = NotificationCompat.PRIORITY_MIN
                    notificationImportance = NotificationManager.IMPORTANCE_MIN
                }
            }

            var ledAnimSpeed = 0
            when (sp.getString("led_anim_speed", "Default")) {
                "MAX" -> {
                    ledAnimSpeed = 250
                }
                "HIGH" -> {
                    ledAnimSpeed = 500
                }
                "Default" -> {
                    ledAnimSpeed = 1000
                }
                "LOW" -> {
                    ledAnimSpeed = 2000
                }
                "0" -> {
                    ledAnimSpeed = 1
                }
            }

            val subText = when (sp.getBoolean("left_days_count_switch", false)) {
                true -> {
                    getString(R.string.time_left_value) + " $timeLeft"
                }
                false -> {
                    null
                }
            }

            when (sp.getBoolean("notification_switch", false)) {
                true -> {
                    val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                        val name = getString(R.string.result_channel_name)
                        val importance = notificationImportance
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
                            .setOngoing(notificationOngoing)
                            .setColor(notificationColor)
                            .setShowWhen(false)
                            .setSubText(subText)
                            .setPriority(notificationPriority)
                            .setContentIntent(pendingIntent)
                            .setSound(notificationSound)
                            .setLights(notificationLedColor, ledAnimSpeed, ledAnimSpeed)
                            .setDefaults(FLAG_SHOW_LIGHTS)
                        notificationManager.notify(1, notificationBuilder.build())
                        notificationVibration()
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
                            .setSubText(subText)
                            .setProgress(100, curCharge, false)
                            .setOngoing(notificationOngoing)
                            .setColor(notificationColor)
                            .setShowWhen(false)
                            .setPriority(notificationPriority)
                            .setContentIntent(pendingIntent)
                            .setSound(notificationSound)
                            .setDefaults(FLAG_SHOW_LIGHTS)
                            .setLights(notificationLedColor, ledAnimSpeed, ledAnimSpeed)
                        notificationManager.notify(1, notificationBuilder.build())
                        notificationVibration()
                    }
                }
                false -> {
                    notificationManager.cancelAll()
                }
            }
        }
        return true
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
                            editor.putInt(
                                "curChargeBackup",
                                curCharge.toInt()
                            )
                            editor.putString(
                                "timeLeftBackup",
                                timeAfterCharge.toInt().toString().trim()
                            )
                            editor.putString(
                                "remainingTimeBackup",
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
                editor.putInt(
                    "curChargeBackup",
                    curCharge.toInt()
                )
                editor.putString(
                    "timeLeftBackup",
                    timeAfterCharge.toInt().toString().trim()
                )
                editor.putString(
                    "remainingTimeBackup",
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
        }
        return super.onOptionsItemSelected(item)
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

