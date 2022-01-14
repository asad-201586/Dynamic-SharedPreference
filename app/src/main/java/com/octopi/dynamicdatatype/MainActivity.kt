package com.octopi.dynamicdatatype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    var TAG = "MainAct"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPrefs().set("key1","Hello World")
        getPrefs().set("key2",true)
        getPrefs().set("key3",20.55)
        getPrefs().set("key4",1555)

        getPrefs().setPrefObject("object1",MyModel("asad","2016-17","ICE",26))
        getPrefs().setPrefObject("object2",MyModel("shuvo","2016-17","ICE",30))

        val strValue = getPrefs().get("key1","")
        val booleanValue = getPrefs().get("key2",false)
        val longValue = getPrefs().get("key3",0.0)
        val intValue = getPrefs().get("key4",0)

        val objectOne: MyModel = getPrefs().getPrefObject("object1")
        val objectTwo: MyModel = getPrefs().getPrefObject("object2")

        Log.d(TAG, "onCreate: string value: $strValue")
        Log.d(TAG, "onCreate: boolean value: $booleanValue")
        Log.d(TAG, "onCreate: long value: $longValue")
        Log.d(TAG, "onCreate: int value: $intValue")
        Log.d(TAG, "onCreate: objectOne value: ${Gson().toJson(objectOne)}")
        Log.d(TAG, "onCreate: ObjectTwo value: ${Gson().toJson(objectTwo)}")

    }
}