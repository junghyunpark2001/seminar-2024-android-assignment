package com.wafflestudio.waffleseminar2024.data.database

import androidx.databinding.adapters.Converters
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.wafflestudio.waffleseminar2024.Company
import com.wafflestudio.waffleseminar2024.Country
import com.wafflestudio.waffleseminar2024.Genre
import com.wafflestudio.waffleseminar2024.Language

@Entity(tableName = "favorite_movie_ids")
data class FavoriteMovieId(
    @PrimaryKey val id: Int
)

@Entity(tableName = "example_table2")
@TypeConverters(MyConverters::class)
data class MyEntity(
    @PrimaryKey val id: Int?,
    val title: String?,
    val original_title: String?,
    val backdrop_path: String?,
    val budget: Int?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val vote_average: Double?,
    val genres: List<Genre>?,
    val homepage: String?,
    val original_language: String?,
    val popularity: Float?,
    val production_companies: String?,
    val production_countries: String?,
    val spoken_languages: String?,
    val tagline: String?,
    val vote_count: Int?
)