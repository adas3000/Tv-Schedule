package pl.tv.channellist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.tv.channellist.R
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.view.ui.util.IProgramme

class ProgrammeAdapter(private var programeList: List<TvProgramme>,val iProgramme: IProgramme):RecyclerView.Adapter<ProgrammeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.programme_item,parent,false))
    }

    override fun getItemCount(): Int {
        return programeList.size
    }

    fun setProgrammeList(programeList: List<TvProgramme>){
        this.programeList = programeList
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.programmeName.text = programeList[position].name

        holder.programmeName.setOnClickListener {
            iProgramme.onProgrammeClick(programeList[position].movieList)
        }

        //todo load logo
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val programmeName = view.findViewById<TextView>(R.id.textView_programme_name)
        val programmeLogo = view.findViewById<ImageView>(R.id.imageView_programme_logo)
    }


}