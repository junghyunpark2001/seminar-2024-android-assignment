package com.wafflestudio.waffleseminar2024.dependencies

import com.wafflestudio.waffleseminar2024.API.TMDBService
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieSearchResponse
import retrofit2.Response

class ITMDBServiceAdapter(private val tmdbService: TMDBService) : ITMDBService {
    override suspend fun getMovieDetails(id: Int): Response<Movie> {
        return tmdbService.getMovieDetails(id)
    }

    override suspend fun searchMoviesByTitle(title: String): Response<MovieSearchResponse> {
        return tmdbService.searchMoviesByTitle(title)
    }

    override suspend fun discoverMoviesByGenre(genreId: Int): Response<MovieSearchResponse> {
        return tmdbService.discoverMoviesByGenre(genreId)
    }
}
