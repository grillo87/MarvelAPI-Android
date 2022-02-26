package com.josegrillo.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.josegrillo.usecase.entity.CharacterResult

interface MarvelApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
        @Query("orderBy") orderBy: String = "name"
    ): Response<CharacterResult>

    @GET("characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int
    ): Response<CharacterResult>
}