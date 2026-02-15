package com.projet.sncf

import com.projet.sncf.SncfResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SncfApiService {
    @GET("coverage/sncf/places")
    suspend fun getPlaces(
        @Header("Authorization") authKey: String,
        @Query("q") query: String
    ): PlacesResponse

    @GET("coverage/sncf/journeys")
    suspend fun getJourneys(
        @Header("Authorization") authKey: String,
        @Query("from") depart: String,
        @Query("to") arrivee: String
    ): SncfResponse
}

