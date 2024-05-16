package com.alejandro.frikiapp.rickandmorty

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceRMCharacters {
    @GET("/api/character")
    suspend fun getRickyCharacter(@Query("name")characterName:String): Response<RickMortyDataResponse>
}
