package com.josegrillo.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.josegrillo.data.entity.CharacterResultDTO

interface MarvelApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
        @Query("orderBy") orderBy: String = "name"
    ): Response<CharacterResultDTO>

    @GET("characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int
    ): Response<CharacterResultDTO>
}