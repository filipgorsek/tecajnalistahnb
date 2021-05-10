package com.filip.tecajnalistahnb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filip.tecajnalistahnb.R
import com.filip.tecajnalistahnb.data.model.ExchangeRateModel

class CurrencyListAdapter() : RecyclerView.Adapter<CurrencyHolder>() {

    private val currencyList: MutableList<ExchangeRateModel> = mutableListOf()

    fun setData(movieList: MutableList<ExchangeRateModel>) {
        this.currencyList.clear()
        this.currencyList.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_exchange_rate, parent, false)
        val binding = com.filip.tecajnalistahnb.databinding.ItemExchangeRateBinding.bind(view)
        return CurrencyHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.bindData(currencyList[position])
    }

    override fun getItemCount(): Int = currencyList.size
}