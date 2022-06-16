package com.triton.johnsonapp.utils

import android.content.Context
import android.content.SharedPreferences

class CommonUtil (context: Context?) {
    companion object {
        const val MyPREFERENCES = "Preference"
        lateinit var pref: SharedPreferences
        var context: Context? = null
        var dbHelper : DBHelper? = null
        var dbUtil : DbUtil? = null
        const val ISLOGIN = "islogin"
        const val MOBILE = "mobile"
        const val USERTYPE = "usertype"

    }

    //https://abhiandroid.com/materialdesign/animation
    init {
        Companion.context = context
        pref = Companion.context!!.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
    }
}