package com.wafflestudio.waffleseminar2024.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movie = MutableLiveData<Movie?>()
    val movie: LiveData<Movie?> get() = _movie

    private val _searchResults = MutableLiveData<List<Movie>>()
    val searchResults: LiveData<List<Movie>> get() = _searchResults

    fun fetchMovieDetails(id: Int) {
        viewModelScope.launch {
            // 네트워크 작업은 IO 스레드에서 수행
            val movieDetails = withContext(Dispatchers.IO) {
                repository.getMovieById(id)  // API를 통해 영화 세부 정보 가져오기
            }
            _movie.value = movieDetails  // LiveData에 영화 세부 정보 설정
        }
    }

    fun titleQuery(titleWord: String) {
        viewModelScope.launch {
            // 네트워크 작업은 IO 스레드에서 수행
            val movies = withContext(Dispatchers.IO) {
                repository.getMoviesByTitle(titleWord)  // API를 통해 영화 검색
            }
            _searchResults.value = movies ?: emptyList()  // 검색 결과 업데이트
        }
    }

    fun genreQuery(genreId: Int) {
        viewModelScope.launch {
            // 네트워크 작업은 IO 스레드에서 수행
            val movies = withContext(Dispatchers.IO) {
                repository.getMoviesByGenre(genreId)  // API를 통해 장르별 영화 검색
            }
            _searchResults.value = movies ?: emptyList()  // 검색 결과 업데이트
        }
    }
}
