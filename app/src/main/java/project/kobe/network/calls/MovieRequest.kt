package project.kobe.network.calls

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.agrawalsuneet.dotsloader.loaders.TrailingCircularDotsLoader
import com.squareup.picasso.Picasso
import project.kobe.R
import project.kobe.model.Movie
import project.kobe.network.RetrofitInitializer
import project.kobe.persistence.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieRequest {

    fun requestMovie(
        id: Int,
        context: Context,
        imgFolderDetails: ImageView,
        txtNomeDatails: TextView,
        txtGenreDetails: TextView,
        txtOverview: TextView,
        txtReleaseDetails: TextView,
        progress: TrailingCircularDotsLoader
        ){
        val call = RetrofitInitializer().MovieService().getInfoMovie(id, context.getString(R.string.aṕi_key))
        call.enqueue(object : Callback<Movie>{

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                Picasso.get().load("http://image.tmdb.org/t/p/w500"+ response.body()!!.posterPath)
                    .into(imgFolderDetails)

                txtNomeDatails.append(response.body()!!.title)

                val startRoom: AppDatabase? = AppDatabase.getInstance(context)

                var genreItem = ""
                val size = response.body()!!.genres.size
                for (i in 0 until size){
                    genreItem += startRoom!!.genreDao().getGenreId(response.body()!!.genres[i].id) + "\n"
                }

                txtGenreDetails.append(genreItem)

                txtOverview.append(response.body()!!.overview)

                txtReleaseDetails.append(response.body()!!.releaseDate)

                progress.visibility = View.GONE
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("log failure", t.message)
                progress.visibility = View.GONE
            }

        })
    }
}