package com.josegrillo.data.network

import com.josegrillo.data.entity.ResultDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
        @Query("orderBy") orderBy: String = "name"
    ): Single<ResultDTO>

    @GET("characters/{characterId}")
    fun getCharacterDetail(
        @Path("characterId") characterId: Int
    ): Single<ResultDTO>
}