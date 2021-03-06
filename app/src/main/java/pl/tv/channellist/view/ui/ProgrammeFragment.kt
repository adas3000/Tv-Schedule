package pl.tv.channellist.view.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_programmes.*
import pl.tv.channellist.R
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.view.adapter.ProgrammeAdapter
import pl.tv.channellist.view.ui.util.IProgramme
import pl.tv.channellist.viewmodel.ProgrammeViewModel

class ProgrammeFragment :Fragment(),IProgramme {


    private lateinit var viewModel:ProgrammeViewModel
    private lateinit var navController: NavController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_programmes,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProviders.of(activity as FragmentActivity).get(ProgrammeViewModel::class.java)

        val adapter = ProgrammeAdapter(listOf(), this)
        recyclerView_programmes.adapter = adapter
        recyclerView_programmes.layoutManager = LinearLayoutManager(context)


        viewModel.programmeLiveData.observe(this, Observer<List<TvProgramme>> {
            adapter.setProgrammeList(it)
        })

    }

    override fun onProgrammeClick(clickedProgramme: TvProgramme) {
        viewModel.setCurrentProgramme(clickedProgramme)
        navController.navigate(R.id.action_programme_to_movies)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }



}