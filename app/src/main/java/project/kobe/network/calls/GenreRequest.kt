package project.kobe.network.calls

import android.content.Context
import android.util.Log
import project.kobe.R
import project.kobe.model.Genre
import project.kobe.network.RetrofitInitializer
import project.kobe.persistence.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GenreRequest {

    var startRoom : AppDatabase? = null

    fun requestGenre(
        context: Context
    ){
        val call = RetrofitInitializer().MovieService().getListGenre(context.getString(R.string.aṕi_key))

        call.enqueue(object : Callback<Genre>{
            override fun onResponse(call: Call<Genre>, response: Response<Genre>) {

                if(response.code() == 200){
                    startRoom = AppDatabase.getInstance(context)
                    startRoom!!.genreDao().add(response.body()!!.genres)
                }
            }

            override fun onFailure(call: Call<Genre>, t: Throwable) {
                Log.i("log failure", t.message)
            }
        })

    }
}