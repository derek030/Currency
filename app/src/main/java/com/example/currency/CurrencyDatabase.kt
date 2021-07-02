package com.example.currency

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Created by Derek Chan on 2/7/2021.
 */
@Database(entities = [CurrencyEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        private var instance: CurrencyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CurrencyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, CurrencyDatabase::class.java,
                    "currency_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }

            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d("testing", "Database onCreate")
            }
        }

//        private fun populateDatabase(db: CurrencyDatabase) {
//                currencyDao.insert(Currency(1, "Bitcoin", "BTC"))
//                currencyDao.insert(Currency(2, "Ethereum", "ETH"))
//                currencyDao.insert(Currency(3, "XRP", "XRP"))
//                currencyDao.insert(Currency(4, "Bitcoin Cash", "BCH"))
//                currencyDao.insert(Currency(5, "Litecoin", "LTC"))
//                currencyDao.insert(Currency(6, "EOS", "EOS"))
//                currencyDao.insert(Currency(7, "Binance Coin", "BNB"))
//                currencyDao.insert(Currency(8, "Chainlink", "LINK"))
//                currencyDao.insert(Currency(9, "NEO", "NEO"))
//                currencyDao.insert(Currency(10, "Ethereum Classic", "ETC"))
//                currencyDao.insert(Currency(11, "Ontology", "ONT"))
//                currencyDao.insert(Currency(12, "Crypto.com Chain", "CRO"))
//                currencyDao.insert(Currency(13, "Cucumber", "CUC"))
//                currencyDao.insert(Currency(14, "USD Coin", "USDC"))
//          }
    }
}