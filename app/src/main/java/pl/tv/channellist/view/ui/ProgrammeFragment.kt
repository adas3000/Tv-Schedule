package pl.tv.channellist.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.tv.channellist.R
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.viewmodel.ProgrammeViewModel

class ProgrammeFragment :Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_programmes,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)

        viewModel.programmeLiveData.observe(viewLifecycleOwner,Observer<List<TvProgramme>>{
                println("set !!")
        })




    }

}