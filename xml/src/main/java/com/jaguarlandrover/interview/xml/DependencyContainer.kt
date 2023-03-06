package com.jaguarlandrover.interview.xml

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object DependencyContainer {

    @OptIn(ExperimentalSerializationApi::class)
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(MediaType.parse("application/json")!!))
            .build()
    }

    val vehiclesService: VehiclesService by lazy {
        retrofit.create(VehiclesService::class.java)
    }
}