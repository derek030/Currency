package com.example.currency

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_currency_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Derek Chan on 2/7/2021.
 */
class CurrencyListFragment : Fragment() {

    private lateinit var viewModel: CurrencyListViewModel
    private lateinit var dataList: List<CurrencyEntity>
    private lateinit var sortedList: List<CurrencyEntity>
    private lateinit var adapter: CurrencyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity()).get(CurrencyListViewModel::class.java)

        viewModel.getAllCurrencies().observe(viewLifecycleOwner, Observer {
            dataList = it
        })

        viewModel.getAllSortedCurrencies().observe(viewLifecycleOwner, Observer {
            sortedList = it
        })

        initUI()
    }

    private fun initUI() {
        btnLoad.setOnClickListener {
            it.isEnabled = false
            Log.d("CurrencyListFragment", "false")
            adapter.updateData(dataList)
            CoroutineScope(Dispatchers.Main).launch {
                adapter.notifyDataSetChanged()
                it.isEnabled = true
                Log.d("CurrencyListFragment", "true")
            }
        }
        btnSort.setOnClickListener {
            it.isEnabled = false
            adapter.updateData(sortedList)
            CoroutineScope(Dispatchers.Main).launch {
                adapter.notifyDataSetChanged()
                it.isEnabled = true
            }
        }
        rvContent.layoutManager = LinearLayoutManager(requireContext())
        adapter = CurrencyListAdapter(emptyList())
        rvContent.adapter = adapter
    }
}