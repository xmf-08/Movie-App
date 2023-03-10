package com.example.movieapp.db

import android.content.Context
import android.content.SharedPreferences
import com.example.movieapp.models.MyMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
        private const val NAME = "catch_filee_name"
        private const val MODE = Context.MODE_PRIVATE

        private lateinit var preferences: SharedPreferences

        fun init(context: Context){
            preferences = context.getSharedPreferences(NAME, MODE)
        }

        private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor) -> Unit){
            val editor = edit()
            operation(editor)
            editor.apply()
        }

        var myName:String?
            get() = preferences.getString("myName", "")
            set(value) = preferences.edit{
                if (value!=null){
                    it.putString( "myName", value)
                 }
            }
        var catchList:ArrayList<MyMovie>
        get() = gsonStringToArrayList(preferences.getString("keyList", "[]")!!)
        set(value) = preferences.edit {
            if (value!=null){
                it.putString("keyList", arrayListToGsonString(value))
            }
        }
            fun arrayListToGsonString(list:ArrayList<MyMovie>):String{
                var gson = Gson()
                return gson.toJson(list)
            }
        fun gsonStringToArrayList(str:String):ArrayList<MyMovie>{
            var gson = Gson()
            val type = object : TypeToken<ArrayList<MyMovie>>(){}.type
           return gson.fromJson(str,type)
        }
}