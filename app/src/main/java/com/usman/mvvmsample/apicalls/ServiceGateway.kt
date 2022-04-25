package com.usman.mvvmsample.apicalls

import com.usman.mvvmsample.features.main.model.DogBreeds
import com.usman.mvvmsample.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ServiceGateway {
    @Headers("x-api-key:${Constants.API_KEY}")
    @GET("breeds")
    suspend fun getDogBreeds(@Query("page") page: Int = 1, @Query("limit") limit: Int = 40): List<DogBreeds>?

}
