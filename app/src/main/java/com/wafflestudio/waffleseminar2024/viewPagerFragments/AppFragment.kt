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
import com.wafflestudio.waffleseminar2024.adapter.searchResultRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.databinding.PageAppBinding

class AppFragment : Fragment() {
    private var _binding: PageAppBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by activityViewModels { MovieViewModelFactory(requireContext()) }
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // View Binding을 사용하여 바인딩 객체 초기화
        _binding = PageAppBinding.inflate(inflater, container, false)

        // NavController 초기화: NavHostFragment를 childFragmentManager에서 가져옵니다.
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Room DB에서 찜한 영화 ID 목록을 가져옵니다.
        viewModel.getFavoriteMovieIds()

        // 영화 제목 가져오기
        viewModel.favoriteMovieTitles.observe(viewLifecycleOwner) { movieTitles ->
            Log.d("update?", "yes")
            Log.d("movieTitles", movieTitles.toString())
            val titlesText = movieTitles.joinToString("\n")
            binding.titlesTextView.text = titlesText // 바인딩 객체를 통해 TextView에 접근
        }

        // 버튼 클릭 이벤트 설정
        binding.navigateButton.setOnClickListener {
            navController.navigate(R.id.action_gameFragment_to_newFragment)
        }

        // 추가적인 ViewModel 관찰자 설정
        viewModel.favoriteMovies.observe(viewLifecycleOwner) { movies ->
            // 필요에 따라 movies 처리
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 바인딩 객체 해제
    }
}
