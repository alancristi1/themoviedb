package project.kobe.ui.main

import android.R
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.agrawalsuneet.dotsloader.loaders.TrailingCircularDotsLoader
import kotlinx.android.synthetic.main.activity_main.*
import project.kobe.network.calls.GenreRequest
import project.kobe.network.calls.UpcomingRequest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(project.kobe.R.layout.activity_main)

        var page = 1

        GenreRequest.requestGenre(baseContext)
        refreshList(page)

        btnPrevious.visibility = View.INVISIBLE

        btnNext.setOnClickListener {
            page ++
            refreshList(page)
            btnPrevious.visibility = View.VISIBLE
        }
            btnPrevious.setOnClickListener {
            page --
            if(page == 1){
                btnPrevious.visibility = View.INVISIBLE
                refreshList(page)
            }else{
                refreshList(page)
            }
        }
    }

    fun refreshList(page : Int){
        val progress = startProgress()
        UpcomingRequest.requestUpcoming(baseContext, upcoming, page, progress)
    }

    private fun startProgress(): TrailingCircularDotsLoader {
        val progress = TrailingCircularDotsLoader(
            this,
            11,
            ContextCompat.getColor(this, R.color.holo_blue_dark),
            150,
            10)
            .apply {
                animDuration = 1200
                animDelay = 10
            }

        layoutProgress.addView(progress)
        return progress
    }
}