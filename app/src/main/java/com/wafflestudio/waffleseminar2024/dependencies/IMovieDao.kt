package com.wafflestudio.waffleseminar2024.dependencies

import androidx.lifecycle.LiveData
import com.wafflestudio.waffleseminar2024.data.database.FavoriteMovieId

interface IMovieDao {
    suspend fun insertFavoriteMovieId(movie: FavoriteMovieId)
    suspend fun deleteFavoriteMovieById(movieId: Int)
    suspend fun getAllFavoriteMovieIds(): List<Int>
    suspend fun getAllFavoriteMovieIdsLiveData(): LiveData<List<Int>>
}
