package com.example.mvvmtest.model.remote

import com.example.mvvmtest.model.CocktailDetails
import com.example.mvvmtest.model.CocktailSearch
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit dependencies
 * Retrofit Interface (Service)
 *
 */

//

interface Service{
    @GET(ENDPOINT_SEARCH)
    fun queryCocktailByName(
        @Query(ARG_SEARCH) searchInput:String
    ): Call<CocktailSearch>

    @GET(ENDPOINT_DETAIL)
    fun queryCocktailDetails(
        @Query(ARG_DETAIL) cocktailID:String
    ): Call<CocktailDetails>
}