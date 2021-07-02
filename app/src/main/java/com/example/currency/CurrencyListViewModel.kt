package com.example.currency

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

/**
 * Created by Derek Chan on 2/7/2021.
 */
class CurrencyListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CurrencyRepository(application)
    private val allCurrencies = repository.getAllCurrencies()
    private val allSortedCurrencies = repository.getAllSortedCurrencies()

    fun getAllCurrencies(): LiveData<List<CurrencyEntity>> {
        return allCurrencies
    }

    fun getAllSortedCurrencies(): LiveData<List<CurrencyEntity>> {
        return allSortedCurrencies
    }
}