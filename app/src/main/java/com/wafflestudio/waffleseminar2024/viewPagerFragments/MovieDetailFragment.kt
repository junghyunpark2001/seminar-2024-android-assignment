package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.wafflestudio.waffleseminar2024.Genre
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.adapter.GenreChipAdapter
import com.wafflestudio.waffleseminar2024.databinding.FragmentMovieDetailBinding
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModelFactory
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
class MovieDetailFragment : Fragment() {
    private lateinit var navController: NavController
    private val viewModel: MovieViewModel by activityViewModels { MovieViewModelFactory(requireContext()) }
    private val movieId: Int by lazy {
        arguments?.getInt("movieId") ?: 0
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var genreChipAdapter: GenreChipAdapter
    private var isFavorite = false // 찜 상태를 변수로 관리


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()

        viewModel.fetchMovieDetails(movieId)
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            movie?.let {
                it.genres?.let { genres -> setupRecyclerView(genres) }
                binding.movieTitle.text = it.title
                binding.backdropImg.load("https://image.tmdb.org/t/p/original" + it.backdrop_path)
                binding.posterImg.load("https://image.tmdb.org/t/p/original" + it.poster_path)
                binding.ratingBar.rating = (it.vote_average ?: 10.0).toFloat() / 2
                binding.rateText.text = String.format("%.1f", it.vote_average ?: 0.0)
                binding.runtimeText.text = "${it.runtime?.div(60)}h ${it.runtime?.rem(60)}m"
                binding.releaseyearText.text = it.release_date?.substring(0, 4) ?: ""
                binding.overviewText.text = it.overview
                binding.originaltitleText.text = it.original_title
                binding.statusText.text = it.status
                binding.budgetText.text = DecimalFormat("$#,###").format(it.budget)
                binding.revenueText.text = DecimalFormat("$#,###").format(it.revenue)

                // 찜 상태를 업데이트
                viewModel.isFavorite(movieId).observe(viewLifecycleOwner) { favorite ->
                    isFavorite = favorite
                    updateFavoriteIcon(isFavorite)
                }
            }
        }

        // 찜하기 버튼 클릭 이벤트 추가
        binding.favoriteIcon.setOnClickListener {
            toggleFavorite()
        }
    }

    private fun toggleFavorite() {
        if (isFavorite) {
            viewModel.removeMovieFromFavorites(movieId)
            updateFavoriteIcon(false)
        } else {
            viewModel.addMovieToFavorites(movieId)
            updateFavoriteIcon(true)
        }
        // 찜 상태를 토글
        isFavorite = !isFavorite
    }



    private fun updateFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.favoriteIcon.setImageResource(R.drawable.ic_heart_filled) // 찜한 상태의 아이콘
        } else {
            binding.favoriteIcon.setImageResource(R.drawable.ic_heart_outline) // 찜하지 않은 상태의 아이콘
        }
    }

    private fun setupRecyclerView(data: List<Genre>) {
        genreChipAdapter = GenreChipAdapter(data)
        binding.genreRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.genreRecyclerView.adapter = genreChipAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
