package com.alejandro.frikiapp.rickandmorty

import com.google.gson.annotations.SerializedName

data class RickMortyDataResponse(
    @SerializedName("results") val characters:List<RickMortyItemResponse>
)
data class RickMortyItemResponse(
    @SerializedName("name") val name:String,
    @SerializedName("image") val charactersImage:String
)