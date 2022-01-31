package com.inderjeet.covidtracer.ApiServies

import com.inderjeet.covidtracer.model.AllCountriesCovidData
import com.inderjeet.covidtracer.model.AllCovidData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/v3/covid-19/countries/{country}")
    suspend fun getCovidData(
        @Path("country")countryName:String
    ):AllCovidData

    @GET("/v3/covid-19/countries/")
    suspend fun getAllCountryData():AllCountriesCovidData
}