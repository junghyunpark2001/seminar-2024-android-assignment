package com.wafflestudio.waffleseminar2024.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wafflestudio.waffleseminar2024.API.RetrofitInstance
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyDatabase
import com.wafflestudio.waffleseminar2024.interfaces.ITMDBService
import com.wafflestudio.waffleseminar2024.interfaces.ITMDBServiceAdapter
import com. wafflestudio. waffleseminar2024.API. TMDBService
import com.wafflestudio.waffleseminar2024.interfaces.IMovieDao
import com.wafflestudio.waffleseminar2024.interfaces.MovieDaoImpl

class MovieViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            // TMDBService 가져오기
            val tmdbService: TMDBService = RetrofitInstance.apiService

            // ITMDBServiceAdapter를 사용해 TMDBService를 ITMDBService로 변환
            val tmdbServiceAdapter: ITMDBService = ITMDBServiceAdapter(tmdbService)

            // Room Database 인스턴스를 통해 MovieDao 가져오기
            val database = MyDatabase.getDatabase(context)
            val movieDao = database.myDao()

            val myDao: IMovieDao = MovieDaoImpl(movieDao)


            // MovieRepository 생성 시 필요한 의존성 전달
            val repository = MovieRepository(tmdbServiceAdapter, myDao)
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
