package com.filip.tecajnalistahnb.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.filip.tecajnalistahnb.R
import com.filip.tecajnalistahnb.common.*
import com.filip.tecajnalistahnb.data.model.ExchangeRateModel
import com.filip.tecajnalistahnb.databinding.ItemExchangeRateBinding
import com.filip.tecajnalistahnb.extensions.loadDrawable

class CurrencyHolder(private val binding: ItemExchangeRateBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(model: ExchangeRateModel) {
        binding.run {
            with(model) {
                when (currency) {
                    AUSTRALIA -> ivFlag.loadDrawable(R.drawable.australia)
                    BOSNIA_AND_HERZEGOVINA -> ivFlag.loadDrawable(R.drawable.bosnia_and_herzegovina)
                    CANADA -> ivFlag.loadDrawable(R.drawable.canada)
                    CZECH_REPUBLIC -> ivFlag.loadDrawable(R.drawable.czech_republic)
                    DENMARK -> ivFlag.loadDrawable(R.drawable.denmark)
                    EMU -> ivFlag.loadDrawable(R.drawable.european_union)
                    HUNGARY -> ivFlag.loadDrawable(R.drawable.hungary)
                    JAPAN -> ivFlag.loadDrawable(R.drawable.japan)
                    NORWAY -> ivFlag.loadDrawable(R.drawable.norway)
                    POLAND -> ivFlag.loadDrawable(R.drawable.poland)
                    SWEDEN -> ivFlag.loadDrawable(R.drawable.sweden)
                    SWITZERLAND -> ivFlag.loadDrawable(R.drawable.switzerland)
                    UNITED_KINGDOM -> ivFlag.loadDrawable(R.drawable.united_kingdom)
                    USA -> ivFlag.loadDrawable(R.drawable.united_states)
                    else -> ivFlag.loadDrawable(R.drawable.error_24)
                }
                tvCurrency.text = "($currency = $unit)"
                tvCountry.text = country
                tvBuyingRate.text = "Kupovni tečaj: " + buyingRate
                tvSellingRate.text = "Prodajni tečaj: " + sellingRate
                tvMiddleRate.text = "Srednji tečaj: " + middleRate
            }
        }
    }
}