package com.wafflestudio.waffleseminar2024.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wafflestudio.waffleseminar2024.API.RetrofitInstance
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyDatabase.Companion.getDatabase



class MovieViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            // RetrofitInstance를 통해 TMDBService 가져오기
            val tmdbService = RetrofitInstance.apiService
            val repository = MovieRepository(tmdbService)
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
