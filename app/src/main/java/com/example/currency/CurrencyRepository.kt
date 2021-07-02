package com.example.currency

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Derek Chan on 2/7/2021.
 */
class CurrencyRepository (application: Application) {

    private var currencyDao: CurrencyDao
    private var allCurrencies: LiveData<List<CurrencyEntity>>
    private var allSortedCurrencies: LiveData<List<CurrencyEntity>>

    private val database = CurrencyDatabase.getInstance(application)

    init {
        currencyDao = database.currencyDao()
        updateData(application.applicationContext)
        allCurrencies = currencyDao.getAll()
        allSortedCurrencies = currencyDao.getAllSorted()
    }

    private fun updateData(context: Context) {
        var jsonString = CurrencyUtils.getDataJson(context, "CurrencyList.json")
        Log.d("testing2", jsonString)
        val gson = Gson()
        val listCurrencyType = object : TypeToken<List<CurrencyItem>>() {}.type
        var currencyItems: List<CurrencyItem> = gson.fromJson(jsonString, listCurrencyType)
        CurrencyUtils.subscribeOnBackground {
            currencyItems.forEachIndexed { index, currencyItem ->
                Log.d("testing2", "Id: $index currency: $currencyItem")
                currencyDao.insert(CurrencyEntity(index, currencyItem.name, currencyItem.symbol))
            }
        }
    }

    fun getAllCurrencies(): LiveData<List<CurrencyEntity>> {
        return allCurrencies
    }

    fun getAllSortedCurrencies(): LiveData<List<CurrencyEntity>> {
        return allSortedCurrencies
    }
}