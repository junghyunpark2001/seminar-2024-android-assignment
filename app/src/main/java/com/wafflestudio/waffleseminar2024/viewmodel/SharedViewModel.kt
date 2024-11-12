package com.wafflestudio.waffleseminar2024.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SharedMovieViewModel(application: Application) : AndroidViewModel(application) {
    // 데이터가 업데이트될 때 관찰할 수 있는 LiveData
    val favoriteMovieTitles = MutableLiveData<List<String>>()

    // 찜한 영화 ID 목록 가져오기 등의 메서드 정의
    fun updateFavoriteMovieTitles(newTitles: List<String>) {
        favoriteMovieTitles.value = newTitles
    }
}
