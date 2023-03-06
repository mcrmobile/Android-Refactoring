package com.jaguarlandrover.interview.compose

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import retrofit2.http.GET

const val API_URL = "https://6405b0e040597b65de3df162.mockapi.io/"

interface VehiclesService {

    @GET("vehicles")
    suspend fun getVehicles(): List<Vehicle>
}

@Serializable
@Parcelize
data class Vehicle(
    val vin: String,
    val brand: String,
    val nickname: String,
    val model: String,
    val description: String,
    val colour: String,
    val registration: String,
    val images: Images
) : Parcelable

@Serializable
@Parcelize
data class Images(val primary: String): Parcelable