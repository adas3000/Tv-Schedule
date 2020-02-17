package pl.tv.channellist.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.tv.channellist.R
import pl.tv.channellist.model.data.LiveTvProgramme

class LiveMovieAdapter(var liveMovieList: List<LiveTvProgramme>) :
    RecyclerView.Adapter<LiveMovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.live_movie_item, parent, false))


    override fun getItemCount(): Int = liveMovieList.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.logo.context)
            .load(liveMovieList[position].logoUrl)
            .placeholder(R.drawable.ic_tv_black)
            .into(holder.logo)


        holder.title.text = liveMovieList[position].liveMovie.programmeName
        holder.category.text = liveMovieList[position].liveMovie.programmeCategory
        holder.time.text = liveMovieList[position].liveMovie.hour + "-"+liveMovieList[position].endHour
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textView_live_movie_name)
        val category: TextView = view.findViewById(R.id.textView_live_movie_category)
        val time: TextView = view.findViewById(R.id.textView_live_time)
        val logo: ImageView = view.findViewById(R.id.imageView_live_programme_logo)
    }

}