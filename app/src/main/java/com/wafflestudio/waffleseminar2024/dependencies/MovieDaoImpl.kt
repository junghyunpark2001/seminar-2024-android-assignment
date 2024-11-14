package com.wafflestudio.waffleseminar2024.dependencies

import androidx.lifecycle.LiveData
import com.wafflestudio.waffleseminar2024.data.database.FavoriteMovieId
import com.wafflestudio.waffleseminar2024.data.database.MovieDao

class MovieDaoImpl(private val movieDao: MovieDao) : IMovieDao {

    override suspend fun insertFavoriteMovieId(movie: FavoriteMovieId) {
        movieDao.insertFavoriteMovieId(movie)
    }

    override suspend fun deleteFavoriteMovieById(movieId: Int) {
        movieDao.deleteFavoriteMovieById(movieId)
    }

    override suspend fun getAllFavoriteMovieIds(): List<Int> {
        return movieDao.getAllFavoriteMovieIds()
    }

    override suspend fun getAllFavoriteMovieIdsLiveData(): LiveData<List<Int>> {
        return movieDao.getAllFavoriteMovieIdsLiveData()
    }
}
