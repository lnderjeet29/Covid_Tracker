package com.inderjeet.covidtracer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inderjeet.covidtracer.model.AllCountriesCovidData
import com.inderjeet.covidtracer.model.AllCovidData
import com.inderjeet.covidtracer.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    val CovidData=MutableLiveData<AllCovidData>()
    val AllCountry=MutableLiveData<AllCountriesCovidData>()

    var currentcountryname="india"
    fun getCovidData(CountryName:String){
        currentcountryname=CountryName
        viewModelScope.launch {
            try {
                val covid=Repository.getAllCovidData(CountryName)
                CovidData.postValue(covid)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getAllCountryCovidData(){
        viewModelScope.launch {
            try{
                val country=Repository.getAllCountryData()
                AllCountry.postValue(country)
            }catch (e:Exception){
                Log.e("Api","${e.message}")
                e.printStackTrace()
            }
        }
    }
}