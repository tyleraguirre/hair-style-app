package com.example.haircutapp.util

import android.view.View
import android.view.animation.AnimationUtils
import com.example.haircutapp.R

fun View.fadeInText() {
            val animation = AnimationUtils.loadAnimation(context, R.anim.fadeinscreen)
    this.startAnimation(animation)
}