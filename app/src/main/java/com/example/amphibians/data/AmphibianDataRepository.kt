package com.example.amphibians.data

import com.example.amphibians.model.AmphibianData
import com.example.amphibians.network.AmphibianApiService

interface AmphibianDataRepository {
    suspend fun getAmphibians(): List<AmphibianData>
}

class NetworkAmphibianDataRepository(
    private val apiService: AmphibianApiService
): AmphibianDataRepository {
    override suspend fun getAmphibians(): List<AmphibianData> {
        return apiService.getAmphibians()
    }

}