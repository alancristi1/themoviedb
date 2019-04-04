package project.kobe.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import project.kobe.network.calls.GenreRequest
import project.kobe.network.calls.UpcomingRequest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(project.kobe.R.layout.activity_main)

        GenreRequest.requestGenre(baseContext)
        UpcomingRequest.requestUpcoming(baseContext, upcoming, progress)
    }
}