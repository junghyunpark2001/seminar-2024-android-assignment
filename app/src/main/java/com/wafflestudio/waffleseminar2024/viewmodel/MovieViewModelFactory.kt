package com.wafflestudio.waffleseminar2024.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wafflestudio.waffleseminar2024.API.RetrofitInstance
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyDatabase
import com.wafflestudio.waffleseminar2024.data.database.MyDatabase.Companion.getDatabase


class MovieViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            // RetrofitInstance를 통해 TMDBService 가져오기
            val tmdbService = RetrofitInstance.apiService

            // Room Database 인스턴스를 통해 MyDao 가져오기
            val database = MyDatabase.getDatabase(context)
            val myDao = database.myDao()

            // 두 가지 데이터 소스를 포함하는 Repository 생성
            val repository = MovieRepository(tmdbService, myDao)
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
