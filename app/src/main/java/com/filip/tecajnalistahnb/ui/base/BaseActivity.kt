package com.filip.tecajnalistahnb.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<BindingType : ViewBinding> : AppCompatActivity() {

    private var mBinding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> BindingType

    val binding: BindingType
        get() = mBinding as BindingType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(mBinding).root)
    }
}