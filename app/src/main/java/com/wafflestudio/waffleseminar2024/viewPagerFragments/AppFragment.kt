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
import androidx.navigation.fragment.NavHostFragment
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
import com.wafflestudio.waffleseminar2024.adapter.favoriteRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.databinding.PageAppBinding

class AppFragment : Fragment() {
    private var _binding: PageAppBinding? = null
    private val binding get() = _binding!!
    lateinit var favoriteRecyclerView: RecyclerView

    private val viewModel: MovieViewModel by activityViewModels { MovieViewModelFactory(requireContext()) }
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // View Binding을 사용하여 바인딩 객체 초기화
        _binding = PageAppBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSearchResultRecyclerView()
        navController = findNavController()

        // Room DB에서 찜한 영화 ID 목록을 가져옵니다.
        viewModel.getFavoriteMovieIds()



        // 추가적인 ViewModel 관찰자 설정
        viewModel.favoriteMovies.observe(viewLifecycleOwner) { movies ->
            // 필요에 따라 movies 처리
            Log.d("movies", movies.toString())

            showResult(movies)
        }
    }
    private fun setSearchResultRecyclerView(){
        favoriteRecyclerView = binding.favoriteRecyclerView
    }

    private fun showResult(data: List<Movie>) {
        Log.d("show", "into function")
        favoriteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        favoriteRecyclerView.adapter = favoriteRecyclerViewAdapter(data) { movie ->
            val action = SearchResultFragmentDirections.actionToMovieDetailFragment(movie.id)
            navController.navigate(action)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 바인딩 객체 해제
    }
}
