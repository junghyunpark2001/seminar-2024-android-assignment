package com.wafflestudio.waffleseminar2024.API

import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Path

interface TMDBService {
    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): Response<Movie>  // 반환 타입이 Response<Movie>여야 합니다

    @GET("search/movie")
    suspend fun searchMoviesByTitle(
        @Query("query") title: String
    ): Response<MovieSearchResponse>  // 반환 타입이 Response<MovieSearchResponse>여야 합니다

    @GET("discover/movie")
    suspend fun discoverMoviesByGenre(
        @Query("with_genres") genreId: Int
    ): Response<MovieSearchResponse>  // 반환 타입이 Response<MovieSearchResponse>여야 합니다
}
