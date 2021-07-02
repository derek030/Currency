package com.example.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Created by Derek Chan on 2/7/2021.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, CurrencyListFragment()).commit()
    }
}