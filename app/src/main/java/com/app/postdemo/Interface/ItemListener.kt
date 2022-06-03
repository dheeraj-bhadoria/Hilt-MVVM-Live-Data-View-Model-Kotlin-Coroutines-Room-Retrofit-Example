package com.app.postdemo.Interface

import android.content.Context

interface ItemListener {
    fun itemClicked(position:Int, context: Context)
}