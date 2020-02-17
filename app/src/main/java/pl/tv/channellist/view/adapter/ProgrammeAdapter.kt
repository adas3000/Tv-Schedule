package pl.tv.channellist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

        Glide.with(holder.programmeLogo.context)
            .load(programeList[position].logoUrl)
            .placeholder(R.drawable.ic_tv_black)
            .into(holder.programmeLogo)


        holder.programmeName.text = programeList[position].name.toUpperCase()

        holder.programmeName.setOnClickListener {
            iProgramme.onProgrammeClick(programeList[position])
        }


    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val programmeName:TextView = view.findViewById(R.id.textView_programme_name)
        val programmeLogo:ImageView = view.findViewById(R.id.imageView_programme_logo)
    }


}