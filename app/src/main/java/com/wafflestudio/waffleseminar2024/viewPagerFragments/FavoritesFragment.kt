package com.wafflestudio.waffleseminar2024.viewPagerFragments

//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.RecyclerView
//import com.wafflestudio.waffleseminar2024.Movie
//
//class FavoritesFragment : Fragment() {
//    private lateinit var movieDao: MovieDao
//    private lateinit var favoriteMovies: List<Movie>
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        movieDao = // ROOM DB 초기화 코드 작성
//                // favoriteMovies = movieDao.getFavoriteMovies()
//
//        recyclerView = view.findViewById(R.id.favoriteMoviesRecyclerView)
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
//        recyclerView.adapter = MovieRecyclerViewAdapter(favoriteMovies) { movie ->
//            // 상세 화면으로 이동하는 코드
//            val action = FavoritesFragmentDirections.actionToMovieDetailFragment(movie.id)
//            findNavController().navigate(action)
//        }
//    }
//}
