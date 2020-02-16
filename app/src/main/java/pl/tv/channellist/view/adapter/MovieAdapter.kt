package pl.tv.channellist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.tv.channellist.R
import pl.tv.channellist.model.data.TvProgramme

class MovieAdapter(var programmeList:List<TvProgramme>):RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false))
    }

    override fun getItemCount(): Int {
        return programmeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.title.text = programmeList[position].movieList.
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.textView_title)
        val time = view.findViewById<TextView>(R.id.textView_time)
        val category = view.findViewById<TextView>(R.id.textView_category)
    }

}