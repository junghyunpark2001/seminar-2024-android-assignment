package com.wafflestudio.waffleseminar2024.interfaces

import com.wafflestudio.waffleseminar2024.API.TMDBService
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieSearchResponse
import retrofit2.Response

class TMDBServiceImpl(private val retrofitService: TMDBService) : ITMDBService {

    override suspend fun getMovieDetails(id: Int): Response<Movie> {
        // Retrofit의 getMovieDetails 메서드를 호출하여 영화 세부 정보를 가져옴
        return retrofitService.getMovieDetails(id)
    }

    override suspend fun searchMoviesByTitle(title: String): Response<MovieSearchResponse> {
        // Retrofit의 searchMoviesByTitle 메서드를 호출하여 영화 검색 결과를 가져옴
        return retrofitService.searchMoviesByTitle(title)
    }

    override suspend fun discoverMoviesByGenre(genreId: Int): Response<MovieSearchResponse> {
        // Retrofit의 discoverMoviesByGenre 메서드를 호출하여 특정 장르의 영화를 검색
        return retrofitService.discoverMoviesByGenre(genreId)
    }
}
