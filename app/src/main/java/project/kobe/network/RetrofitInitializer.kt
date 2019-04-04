package project.kobe.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import project.kobe.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val url = "https://api.themoviedb.org/3/"

    val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })

        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun MovieService() = retrofit.create(MovieService::class.java)
}