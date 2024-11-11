package com.wafflestudio.waffleseminar2024.viewmodel

import android.util.Log
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

    private val _favoriteMovieTitles = MutableLiveData<List<String>>()
    val favoriteMovieTitles: LiveData<List<String>> get() = _favoriteMovieTitles


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

    fun isFavorite(movieId: Int): LiveData<Boolean> {
        val isFavoriteLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            val favoriteIds = repository.getAllFavoriteMovieIds()
            isFavoriteLiveData.postValue(favoriteIds.contains(movieId))
        }
        return isFavoriteLiveData
    }

    fun addMovieToFavorites(movieId: Int) {
        viewModelScope.launch {
            repository.addFavoriteMovieById(movieId)
            updateFavoriteMovieTitles()
        }
    }

    fun removeMovieFromFavorites(movieId: Int) {
        viewModelScope.launch {
            repository.removeFavoriteMovieById(movieId)
            updateFavoriteMovieTitles()
        }
    }

    // Room DB에서 찜한 영화 ID 목록을 가져와 제목을 가져오는 메서드
    fun getFavoriteMovieIds() {
        viewModelScope.launch {
            updateFavoriteMovieTitles()
        }
    }
    // 토글 시 즉시 제목을 업데이트하는 메서드
    private suspend fun updateFavoriteMovieTitles() {
        Log.d("debug","observe movie update")
        val movieIds = repository.getAllFavoriteMovieIds()
        val movieTitles = mutableListOf<String>()
        for (id in movieIds) {
            val movieDetails = withContext(Dispatchers.IO) {
                repository.getMovieById(id)
            }
            movieDetails?.let { movieTitles.add(it.title) }
        }
        _favoriteMovieTitles.postValue(movieTitles) // 비동기 작업에서는 postValue 사용
        Log.d("debug", "Updated favoriteMovieTitles: $movieTitles")
    }
}