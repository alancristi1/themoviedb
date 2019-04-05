package project.kobe.network.calls

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import com.agrawalsuneet.dotsloader.loaders.TrailingCircularDotsLoader
import project.kobe.R
import project.kobe.adapter.UpcomingAdapter
import project.kobe.model.UpcomingMovie
import project.kobe.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UpcomingRequest {

    fun requestUpcoming(
        context: Context,
        list: RecyclerView,
        page : Int,
        progress: TrailingCircularDotsLoader
    ){

        val call = RetrofitInitializer().MovieService().listUpcoming(context.getString(R.string.aṕi_key), page)

        call.enqueue(object : Callback<UpcomingMovie> {
            override fun onResponse(call: Call<UpcomingMovie>, response: Response<UpcomingMovie>) {
                response.body().let {
                    list.adapter = UpcomingAdapter(response.body()!!.results, context)
                    val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    list.layoutManager = layoutManager

                    progress.visibility = View.GONE

                }
            }

            override fun onFailure(call: Call<UpcomingMovie>, t: Throwable) {
                Log.i("log failure", t.message)
                progress.visibility = View.GONE
            }

        })
    }
}