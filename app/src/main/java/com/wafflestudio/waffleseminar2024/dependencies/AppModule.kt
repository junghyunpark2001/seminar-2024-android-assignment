package com.wafflestudio.waffleseminar2024.dependencies

import android.content.Context
import androidx.room.Room
import com.wafflestudio.waffleseminar2024.data.database.MovieDao
import com.wafflestudio.waffleseminar2024.data.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.wafflestudio.waffleseminar2024.API.TMDBService
import com.wafflestudio.waffleseminar2024.BuildConfig
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalUrl = original.url

                // API 키 추가
                val url = originalUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build()

                val request = original.newBuilder()
                    .url(url)
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideTMDBService(retrofit: Retrofit): TMDBService {
        return retrofit.create(TMDBService::class.java)
    }

    @Provides
    @Singleton
    fun provideITMDBService(tmdbService: TMDBService): ITMDBService {
        return ITMDBServiceAdapter(tmdbService)
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: MyDatabase): MovieDao {
        return database.myDao()
    }

    @Provides
    @Singleton
    fun provideIMovieDao(movieDao: MovieDao): IMovieDao {
        return MovieDaoImpl(movieDao)
    }

    @Provides
    @Singleton
    fun provideMyDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        tmdbService: ITMDBService,
        movieDao: IMovieDao  // IMovieDao로 변경
    ): MovieRepository {
        return MovieRepository(tmdbService, movieDao)
    }
}
