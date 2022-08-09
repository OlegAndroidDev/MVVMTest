package com.example.mvvmtest.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmtest.model.CocktailDetails
import com.example.mvvmtest.model.CocktailSearch
import com.example.mvvmtest.model.remote.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "Cocktail Details"

class CocktailViewModel: ViewModel(){

    private val _cocktailSearchResult =
        MutableLiveData<CocktailSearch>()

    //Backing Field
    val cocktailSearchResult:LiveData<CocktailSearch>
    get()=_cocktailSearchResult

    private val _errorMessages = MutableLiveData("")
    val errorMessages: LiveData<String>
    get()=_errorMessages

    private val _cocktailDetails = MutableLiveData<CocktailDetails>()
    val cocktailDetails: LiveData<CocktailDetails>
    get() = _cocktailDetails

    fun searchCocktail(cocktailName: String){
        API.cocktalApi.queryCocktailByName(cocktailName)
            .enqueue(
                object:Callback<CocktailSearch>{
                    override fun onResponse(
                        call: Call<CocktailSearch>,
                        response: Response<CocktailSearch>
                    ) {

//                        when(response.code()){
//                          in 100..200->{
//
//                          }
//                          in 200 until 300->{
//                              Log.d("Message", "onResponse: Success")
//                          }
//                          505 ->{}
//                        }

                        if(response.isSuccessful){
                            response.body()?.let{
                                _cocktailSearchResult.value =
                                    it
                            }?: kotlin.run{
                                _errorMessages.value = response.message()
                            }
                        }else{
                            _errorMessages.value = response.message()
                        }
                    }

                    override fun onFailure(call: Call<CocktailSearch>,
                                           t: Throwable) {
                        t.printStackTrace()
                        _errorMessages.value = t.message?: "Unknown error"
                        //Toast.makeText()
                    }

                }
            )
    }
    fun getCocktailDetails(cocktailID: String){
        API.cocktalApi.queryCocktailDetails (cocktailID)
            .enqueue(
                //working thread
                object:Callback<CocktailDetails>{
                    override fun onResponse(
                        call: Call<CocktailDetails>,
                        response: Response<CocktailDetails>
                    ) {
                        if(response.isSuccessful){
                            response.body()?.let{
                                _cocktailDetails.value = it
                            }?: kotlin.run{
                                _errorMessages.value = response.message()
                            }
                        }else{
                            _errorMessages.value = response.message()
                        }
                    }

                    override fun onFailure(call: Call<CocktailDetails>, t: Throwable) {
                        t.printStackTrace()
                        _errorMessages.value = t.message?: "Unknown error"
                    }

                }
            )
    }
}