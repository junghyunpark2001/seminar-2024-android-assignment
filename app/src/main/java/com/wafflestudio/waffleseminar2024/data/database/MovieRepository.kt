package com.wafflestudio.waffleseminar2024.data.database

import androidx.lifecycle.LiveData
import com.wafflestudio.waffleseminar2024.API.TMDBService
import com.wafflestudio.waffleseminar2024.Movie


class MovieRepository(
    private val tmdbService: TMDBService,
    private val myDao: MovieDao

) {


    // 영화 ID로 영화 세부 정보를 가져오는 메서드
    suspend fun getMovieById(id: Int): Movie? {
        return try {
            val response = tmdbService.getMovieDetails(id)
            if (response.isSuccessful) {
                response.body() // 성공적으로 데이터를 가져온 경우 반환
            } else {
                null // 오류가 발생한 경우 null 반환
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null // 예외가 발생한 경우 null 반환
        }
    }

    // 영화 제목으로 영화를 검색하는 메서드
    suspend fun getMoviesByTitle(titleWord: String): List<Movie>? {
        return try {
            val response = tmdbService.searchMoviesByTitle(titleWord)
            if (response.isSuccessful) {
                response.body()?.results // 성공적으로 데이터를 가져온 경우 결과 반환
            } else {
                null // 오류가 발생한 경우 null 반환
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null // 예외가 발생한 경우 null 반환
        }
    }

    // 장르 ID로 영화를 검색하는 메서드
    suspend fun getMoviesByGenre(genreId: Int): List<Movie>? {
        return try {
            val response = tmdbService.discoverMoviesByGenre(genreId)
            if (response.isSuccessful) {
                response.body()?.results // 성공적으로 데이터를 가져온 경우 결과 반환
            } else {
                null // 오류가 발생한 경우 null 반환
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null // 예외가 발생한 경우 null 반환
        }
    }

    suspend fun addFavoriteMovieById(movieId: Int) {
        val favoriteMovieId = FavoriteMovieId(id = movieId)
        myDao.insertFavoriteMovieId(favoriteMovieId)
    }

    suspend fun removeFavoriteMovieById(movieId: Int) {
        myDao.deleteFavoriteMovieById(movieId)
    }

    suspend fun getAllFavoriteMovieIds(): List<Int> {
        return myDao.getAllFavoriteMovieIds()
    }
}

