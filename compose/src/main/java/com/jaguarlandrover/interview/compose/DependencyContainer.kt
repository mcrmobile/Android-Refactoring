package com.jaguarlandrover.interview.compose

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object DependencyContainer {

    @OptIn(ExperimentalSerializationApi::class)
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val vehiclesService: VehiclesService by lazy {
        retrofit.create(VehiclesService::class.java)
    }
}