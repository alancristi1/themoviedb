package project.kobe.ui.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*
import project.kobe.R
import project.kobe.network.calls.MovieRequest

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra("id", 0)

        MovieRequest.requestMovie(id, baseContext, imgFolderDetails,
            txtNomeDatails, txtGenreDetails, txtOverview, txtReleaseDetails)
    }
}
