package com.filip.tecajnalistahnb.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline onChange: (T) -> Unit) =
    observe(owner, Observer {
        it?.run { onChange(this) }
    })