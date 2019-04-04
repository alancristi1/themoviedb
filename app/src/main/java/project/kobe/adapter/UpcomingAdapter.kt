package project.kobe.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.upcoming_layout.view.*
import project.kobe.R
import project.kobe.model.Result
import project.kobe.persistence.AppDatabase
import project.kobe.ui.details.DetailsActivity

class UpcomingAdapter(private val date : List<Result>,
                      private val context : Context) : RecyclerView.Adapter<UpcomingAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.upcoming_layout, parent, false)
        return UpcomingAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return date.size
    }

    override fun onBindViewHolder(holder: UpcomingAdapter.ViewHolder, position: Int) {
        val value = date[position]
        holder.bindView(value, context)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindView(data: Result, context: Context){

            val startRoom: AppDatabase? = AppDatabase.getInstance(context)
            var genreItem = ""

            val name = itemView.txtName
            name.text = data.title
            Picasso.get().load("http://image.tmdb.org/t/p/w342"+data.posterPath)
                .into(itemView.imgFolder)

            val size = data.genreIds.size

            for (i in 0 until size){
                genreItem += startRoom!!.genreDao().getGenreId(data.genreIds[i]) + "\n"
            }

            val genre = itemView.txtGenre
            genre.text = genreItem

            val release = itemView.txtRelease
            release.text = data.releaseDate

            itemView.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("id", data.id)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }

}