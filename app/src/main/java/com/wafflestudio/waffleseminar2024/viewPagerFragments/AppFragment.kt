package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchresultBinding
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.wafflestudio.waffleseminar2024.HomeActivity
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.adapter.searchResultRecyclerViewAdapter



class AppFragment : Fragment() {
    private val viewModel: MovieViewModel by activityViewModels { MovieViewModelFactory(requireContext()) }
    private lateinit var titlesTextView: TextView
    private lateinit var navController: NavController

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

        navController = findNavController()

        // 새로운 버튼을 참조합니다.
        val navigateButton: Button = view.findViewById(R.id.navigateButton)


        // 버튼 클릭 시 FavoriteFragment로 이동합니다.
        navigateButton.setOnClickListener {
            navController.navigate(R.id.action_gameFragment_to_newFragment)
        }

        viewModel.favoriteMovies.observe(viewLifecycleOwner){movies ->

        }
    }

}





//
//class AppFragment : Fragment() {
//    private val viewModel: MovieViewModel by viewModels { MovieViewModelFactory(requireContext()) }
//    private lateinit var navController: NavController
//    private var _binding: FragmentSearchresultBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var searchResultRecyclerView: RecyclerView
//
//    private lateinit var titlesTextView: TextView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentSearchresultBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        navController = findNavController()
//
//        // RecyclerView 설정
//        setSearchResultRecyclerView()
//
//        // ViewModel을 사용해 찜한 영화 목록을 가져옵니다.
//        viewModel.getFavoriteMovieIds()
//
//        // 영화 제목 가져오기
//        viewModel.favoriteMovieTitles.observe(viewLifecycleOwner) { movieTitles ->
//            // 영화 제목을 줄바꿈하여 TextView에 설정합니다.
//            Log.d("update?","yes")
//            Log.d("movieTitles", movieTitles.toString())
//            val titlesText = movieTitles.joinToString("\n")
//            titlesTextView.text = titlesText
//        }
//        // 찜한 영화 목록을 관찰하여 RecyclerView에 데이터를 표시합니다.
//        viewModel.favoriteMovies.observe(viewLifecycleOwner) { movies ->
//            showResult(movies)
//        }
//    }
//
//    private fun setSearchResultRecyclerView() {
//        searchResultRecyclerView = binding.searchResultRecyclerView
//        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3) // 3줄로 표시
//    }
//
//    private fun showResult(data: List<Movie>) {
//        searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(data) { movie ->
//            // 영화 상세 페이지로 이동
//            val action = AppFragmentDirections.actionToMovieDetailFragment(movie.id)
//            navController.navigate(action)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
