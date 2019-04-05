package project.kobe.ui.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.agrawalsuneet.dotsloader.loaders.TrailingCircularDotsLoader
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import project.kobe.R
import project.kobe.network.calls.MovieRequest

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra("id", 0)

        val progress = TrailingCircularDotsLoader(
            this,
            11,
            ContextCompat.getColor(this, android.R.color.holo_blue_dark),
            150,
            10)
            .apply {
                animDuration = 1200
                animDelay = 10
            }

        progressInDetails.addView(progress)

        MovieRequest.requestMovie(id, baseContext, imgFolderDetails,
            txtNomeDatails, txtGenreDetails, txtOverview, txtReleaseDetails, progress)
    }
}
