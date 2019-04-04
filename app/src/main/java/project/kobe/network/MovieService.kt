package project.kobe.network

import project.kobe.model.Genre
import project.kobe.model.Movie
import project.kobe.model.UpcomingMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/upcoming")
    fun listUpcoming(@Query("api_key") key: String) : Call<UpcomingMovie>

    @GET("genre/movie/list")
    fun getListGenre(@Query("api_key") key : String) : Call<Genre>

    @GET("movie/{movie_id}")
    fun getInfoMovie(@Path("movie_id") id : Int,
                     @Query("api_key") key : String) : Call<Movie>
}