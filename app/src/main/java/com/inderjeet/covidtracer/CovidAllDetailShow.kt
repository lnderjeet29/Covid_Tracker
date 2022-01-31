package com.inderjeet.covidtracer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inderjeet.covidtracer.databinding.FragmentCovidAllDetailShowBinding
import com.inderjeet.covidtracer.model.AllCovidData
import com.inderjeet.covidtracer.viewmodel.HomeViewModel
import org.eazegraph.lib.models.PieModel

class CovidAllDetailShow : Fragment() {
    private val CovidViewModel:HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val binding by lazy {
        FragmentCovidAllDetailShowBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Viewprovid(CovidViewModel.currentcountryname)
        observe()
        onClick()
        return binding.root
    }

    private fun onClick() {
       binding.countryBox.setOnClickListener {
           FlagBottomSheet{ countryName->
               Viewprovid(countryName)
           }.show(childFragmentManager,"")
       }
    }


    private fun Viewprovid(CountryName: String) {
        CovidViewModel.getCovidData(CountryName)
    }


    private fun observe() {
        CovidViewModel.CovidData.observe(viewLifecycleOwner){
            setPieData(it)
        }
    }

    private fun setPieData(it: AllCovidData) {
        binding.apply {
            pieChart.clearChart()
            pieChart.addPieSlice(it.cases?.let { it1->
                PieModel("cases",it1.toFloat(),ContextCompat.getColor(requireContext(),R.color.yellow))
            })
            pieChart.addPieSlice(it.recovered?.let { it1->
                PieModel("recover",it1.toFloat(),ContextCompat.getColor(requireContext(),R.color.green))
            })
            pieChart.addPieSlice(it.deaths?.let { it1->
                PieModel("deaths",it1.toFloat(),ContextCompat.getColor(requireContext(),R.color.red))
            })
            pieChart.animate()
            pieChart.startAnimation()
            txtCountryName.text=it.country
            txtCaseNumbers.text= it.cases.toString()
            txtRecoverNumbers.text=it.recovered.toString()
            txtDeathsNumbers.text=it.recovered.toString()
        }
    }


}