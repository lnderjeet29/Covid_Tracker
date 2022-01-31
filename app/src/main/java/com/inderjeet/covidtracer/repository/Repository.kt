package com.inderjeet.covidtracer.repository

import com.inderjeet.covidtracer.model.AllCountriesCovidData
import com.inderjeet.covidtracer.model.AllCovidData

object Repository {
    private val apiServie= Network.getApiService()

    suspend fun getAllCovidData(CountryName:String):AllCovidData{
        return apiServie.getCovidData(CountryName)
    }
    suspend fun getAllCountryData():AllCountriesCovidData{
        return apiServie.getAllCountryData()
    }
}