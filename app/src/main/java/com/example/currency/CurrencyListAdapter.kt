package com.example.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_currency.view.*

/**
 * Created by Derek Chan on 2/7/2021.
 */
class CurrencyListAdapter(private var dataList: List<CurrencyEntity>) :
    RecyclerView.Adapter<CurrencyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.ivCurrency.text = currentItem.name?.first().toString()
        holder.tvCurrency.text = currentItem.name
        holder.tvSymbol.text = currentItem.symbol
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(dataList: List<CurrencyEntity>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}

class CurrencyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
) {
    val ivCurrency: TextView = itemView.ivCurrency
    val tvCurrency: TextView = itemView.tvCurrency
    val tvSymbol: TextView = itemView.tvSymbol
}