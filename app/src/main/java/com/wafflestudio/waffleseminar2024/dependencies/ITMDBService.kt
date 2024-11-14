package com.wafflestudio.waffleseminar2024.dependencies

import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieSearchResponse
import retrofit2.Response // import 문 추가
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// ITMDBService 인터페이스
interface ITMDBService {
    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): Response<Movie>

    @GET("search/movie")
    suspend fun searchMoviesByTitle(@Query("query") title: String): Response<MovieSearchResponse>

    @GET("discover/movie")
    suspend fun discoverMoviesByGenre(@Query("with_genres") genreId: Int): Response<MovieSearchResponse>
}

// TMDBService 인터페이스가 ITMDBService를 상속
interface TMDBService : ITMDBService

