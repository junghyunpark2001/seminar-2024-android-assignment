package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModelFactory

class AppFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModels { MovieViewModelFactory(requireContext()) }
    private lateinit var titlesTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.page_app, container, false)
        titlesTextView = view.findViewById(R.id.PageApp) // TextView ID 설정
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(this, MovieViewModelFactory(requireContext()))[MovieViewModel::class.java]

        // Room DB에서 찜한 영화 ID 목록을 가져옵니다.
        viewModel.getFavoriteMovieIds()

        // 영화 제목 가져오기
        viewModel.favoriteMovieTitles.observe(viewLifecycleOwner) { movieTitles ->
            // 영화 제목을 줄바꿈하여 TextView에 설정합니다.
            Log.d("update?","yes")
            Log.d("movieTitles", movieTitles.toString())
            val titlesText = movieTitles.joinToString("\n")
            titlesTextView.text = titlesText
        }
    }
}
