package com.wafflestudio.waffleseminar2024.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wafflestudio.waffleseminar2024.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovieId(favoriteMovieId: FavoriteMovieId)

    @Query("DELETE FROM favorite_movie_ids WHERE id = :movieId")
    suspend fun deleteFavoriteMovieById(movieId: Int)

    @Query("SELECT id FROM favorite_movie_ids")
    suspend fun getAllFavoriteMovieIds(): List<Int>

    // LiveData로 즐겨찾기 영화 ID 목록 반환
    @Query("SELECT id FROM favorite_movie_ids")
    fun getAllFavoriteMovieIdsLiveData(): LiveData<List<Int>>

}