package com.octopi.dynamicdatatype

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.IllegalArgumentException

class SharedPreferenceUtils(context: Context) {

    val mSharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)
    }

    var editor: SharedPreferences.Editor = mSharedPreferences.edit()


    inline fun <reified T: Any> set(key: String, value: T){
        editor.putString(key,value.toString()).also { editor.apply() }
    }

    inline fun <reified T: Any> get(key: String, defaultValue: T): T{

        var value = mSharedPreferences.getString(key,"empty")
        if (value == "empty") value = defaultValue.toString()

        return when (T::class){
            String::class -> value as T
            Int::class -> value?.toInt() as T
            Long::class -> value?.toLong() as T
            Boolean::class -> value?.toBoolean() as T
            Double::class -> value?.toDouble() as T
            else -> throw IllegalArgumentException("This default value is not valid")
        }
    }

    inline fun <reified T: Any> setPrefObject(key: String, value: T){
        set(key, Gson().toJson(value))
    }

    inline fun <reified T: Any> getPrefObject(key: String):T{
        return Gson().fromJson(get(key,""),T::class.java)
    }

}

fun Context.clearPref(){
    val prefs = this.getSharedPreferences(getString(R.string.app_name),Context.MODE_PRIVATE)
    val editor = prefs.edit()
    editor?.clear()?.apply()
}