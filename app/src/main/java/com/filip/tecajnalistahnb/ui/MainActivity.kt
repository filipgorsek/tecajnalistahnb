package com.filip.tecajnalistahnb.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.filip.tecajnalistahnb.data.model.ExchangeRateModel
import com.filip.tecajnalistahnb.databinding.ActivityMainBinding
import com.filip.tecajnalistahnb.extensions.subscribe
import com.filip.tecajnalistahnb.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val currencyViewModel by viewModel<CurrencyViewModel>()
    private val adapter by lazy { CurrencyListAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        currencyViewModel.checkDbData()
        subscribeData()
    }

    private fun initUI() {
        binding.run {
            currencyList.layoutManager = LinearLayoutManager(this@MainActivity)
            currencyList.adapter = adapter
        }
    }

    private fun subscribeData() {
        currencyViewModel.currencyExchangeLiveData.subscribe(this, ::showData)
    }

    @SuppressLint("SetTextI18n")
    private fun showData(currencyList: MutableList<ExchangeRateModel>) {
        adapter.setData(currencyList)
        binding.tvDate.text = """Datum: ${currencyList[0].date}"""
    }
}