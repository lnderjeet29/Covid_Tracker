package com.inderjeet.covidtracer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.inderjeet.covidtracer.adapter.FlagAdapter
import com.inderjeet.covidtracer.databinding.FragmentFlagBottomSheetBinding
import com.inderjeet.covidtracer.viewmodel.HomeViewModel


class FlagBottomSheet(val selectItem:(String)->Unit) : BottomSheetDialogFragment() {
    private val binding by lazy {
        FragmentFlagBottomSheetBinding.inflate(layoutInflater)
    }
    private val FlagAdapter by lazy {
        FlagAdapter{
            selectItem.invoke(it)
            dismiss()
        }
    }
    private val homeViewModel:HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setAdapter()
        observeData()
        getDetail()
        return binding.root
    }

    private fun getDetail() {
        homeViewModel.getAllCountryCovidData()
    }

    private fun setAdapter() {
        binding.apply {
            rcFlagDetail.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            rcFlagDetail.adapter=FlagAdapter
        }
    }
    private fun observeData() {
        homeViewModel.AllCountry.observe(viewLifecycleOwner){
            Log.e("observeData","observing data:{$it}")
            FlagAdapter.submitList(it)
        }
    }
}