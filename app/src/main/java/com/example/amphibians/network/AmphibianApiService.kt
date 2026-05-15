package com.example.amphibians.network

import com.example.amphibians.model.AmphibianData
import retrofit2.http.GET

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibianData>
}