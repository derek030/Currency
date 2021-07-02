package com.example.currency

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Derek Chan on 2/7/2021.
 */
@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getAll(): LiveData<List<CurrencyEntity>>

    @Query("select * from currency order by symbol asc")
    fun getAllSorted(): LiveData<List<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: CurrencyEntity)
}