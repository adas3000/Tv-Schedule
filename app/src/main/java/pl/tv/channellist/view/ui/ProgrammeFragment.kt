package pl.tv.channellist.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_programmes.*
import pl.tv.channellist.R
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.view.adapter.ProgrammeAdapter
import pl.tv.channellist.viewmodel.ProgrammeViewModel

class ProgrammeFragment :Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_programmes,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ProgrammeViewModel::class.java)

        val adapter = ProgrammeAdapter(listOf())
        recyclerView_programmes.adapter = adapter
        recyclerView_programmes.layoutManager = LinearLayoutManager(context)


        viewModel.programmeLiveData.observe(this,Observer<List<TvProgramme>>{
            for(i in it){
                println(i.name)
            }
            adapter.setProgrammeList(it)
        })




    }

}