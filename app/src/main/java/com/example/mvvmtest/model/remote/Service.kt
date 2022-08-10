package com.example.mvvmtest.model.remote

import com.example.mvvmtest.model.CocktailDetails
import com.example.mvvmtest.model.CocktailItem
import com.example.mvvmtest.model.CocktailSearch
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit dependencies
 * Create Retrofit Interface (Service)
 * In the service file create HTTP verbs.
 * Create a Retrofit object. Singleton. API object
 *
 */


//example
//fun deserialize(jsonString: String){
//    val gson = Gson()
//    gson.fromJson<CocktailItem>(jsonString, CocktailItem::class.java)
//}

//Retrofit interface, usually it is a service

interface Service{
    @GET(ENDPOINT_SEARCH)
    fun queryCocktailByName(
        @Query(ARG_SEARCH) searchInput:String
    ): Call<CocktailSearch> //it returns a call from retrofit

    @GET(ENDPOINT_DETAIL)
    fun queryCocktailDetails(
        @Query(ARG_DETAIL) cocktailID:String
    ): Call<CocktailDetails> //it returns a call from retrofit
}

//https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita

//https://www.thecocktaildb.com/ BASE URL
// api/json/v1/1/search.php End point
// ?s=margarita & NEXT = 653 Arguments

//https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
//public const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"