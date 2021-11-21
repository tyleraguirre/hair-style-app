package com.example.haircutapp.util

import android.annotation.SuppressLint
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
    this.alpha = 0.2F
}


fun Button.isNormalState() {
    this.isEnabled = true
}

fun Fragment.logd(message: String){
    Log.d(this::class.java.simpleName, message)
}