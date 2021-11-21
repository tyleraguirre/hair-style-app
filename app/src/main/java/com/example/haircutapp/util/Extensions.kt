package com.example.haircutapp.util

import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.haircutapp.R

fun View.fadeInText() {
            val animation = AnimationUtils.loadAnimation(context, R.anim.fadeinscreen)
    this.startAnimation(animation)
}

fun Button.isBlackedOut() {
    this.isEnabled = false
    this.setBackgroundColor(resources.getColor(com.example.haircutapp.R.color.black))
    this.setTextColor(resources.getColor(com.example.haircutapp.R.color.black))
}

fun Button.isNormalState() {
    this.isEnabled = true
    this.setBackgroundColor(resources.getColor(R.color.darkblue))
    this.setTextColor(resources.getColor(R.color.white))
}

fun Fragment.logd(message: String){
    Log.d(this::class.java.simpleName, message)
}