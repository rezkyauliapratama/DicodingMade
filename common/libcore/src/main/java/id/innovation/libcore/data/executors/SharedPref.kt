package id.innovation.libcore.data.executors

import android.content.Context
import android.content.SharedPreferences

class SharedPref(
    context: Context
){

    companion object {
        internal const val PREFS_NAME = "movieSP"

        private const val DAILY_REMINDER = "DAILY_REMINDER"
        private const val RELEASE_REMINDER = "RELEASE_REMINDER"

    }


    val sharedPref: SharedPreferences

    init {
        sharedPref =  context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var dailyReminder: Boolean
        set(value) = sharedPref.put(DAILY_REMINDER, value)
        get() = sharedPref.get(DAILY_REMINDER, Boolean::class.java)

    var releaseReminder: Boolean
        set(value) = sharedPref.put(RELEASE_REMINDER, value)
        get() = sharedPref.get(RELEASE_REMINDER, Boolean::class.java)


    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    protected fun <T> SharedPreferences.get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> getString((key), "")
            Boolean::class.java -> getBoolean(key, false)
            Float::class.java -> getFloat(key, -1f)
            Double::class.java -> getFloat(key, -1f)
            Int::class.java -> getInt(key, -1)
            Long::class.java -> getLong(key, -1L)
            else -> null
        } as T

    protected fun <T> SharedPreferences.put(key: String, data: T) {
        val editor = edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

}