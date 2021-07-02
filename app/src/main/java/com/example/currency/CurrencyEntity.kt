package com.example.currency

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Derek Chan on 2/7/2021.
 */
@Entity(tableName = "currency")
data class CurrencyEntity (
    @PrimaryKey(autoGenerate = false) val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "symbol") val symbol: String?
)
