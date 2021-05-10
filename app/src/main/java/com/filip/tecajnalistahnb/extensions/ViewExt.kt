package com.filip.tecajnalistahnb.extensions

import android.widget.*
import com.bumptech.glide.Glide

fun ImageView.loadDrawable(res: Int) {
    Glide.with(this).load(res).into(this)
}