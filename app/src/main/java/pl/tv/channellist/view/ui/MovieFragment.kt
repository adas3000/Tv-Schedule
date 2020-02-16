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
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.tv.channellist.R
import pl.tv.channellist.model.data.PendingProgramme
import pl.tv.channellist.view.adapter.MovieAdapter
import pl.tv.channellist.viewmodel.ProgrammeViewModel

class MovieFragment : Fragment() {

    private lateinit var viewModel: ProgrammeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies,container,false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ProgrammeViewModel::class.java)

        val adapter = MovieAdapter(listOf())
        recyclerView_movies.adapter = adapter
        recyclerView_movies.layoutManager = LinearLayoutManager(context)

        viewModel.movieLiveData.observe(this, Observer<List<PendingProgramme>>{
            adapter.setMovies(it)
        })

    }

}