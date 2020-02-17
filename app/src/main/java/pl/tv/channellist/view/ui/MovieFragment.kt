package pl.tv.channellist.view.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.tv.channellist.R
import pl.tv.channellist.model.data.PendingProgramme
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.view.adapter.MovieAdapter
import pl.tv.channellist.viewmodel.ProgrammeViewModel

class MovieFragment : Fragment() {

    companion object{
        const val menuItemId = 0
    }

    private lateinit var viewModel: ProgrammeViewModel
    private lateinit var menu:Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies,container,false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ProgrammeViewModel::class.java)

        val adapter = MovieAdapter(listOf())
        recyclerView_movies.adapter = adapter
        recyclerView_movies.layoutManager = LinearLayoutManager(context)

        viewModel.currentProgrammeLiveData.observe(this, Observer<TvProgramme>{
            adapter.setMovies(it.movieList)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.channels_top_menu,menu)
        if(viewModel.currentProgrammeLiveData.value!!.isFavourite){
            menu.getItem(menuItemId).setIcon(R.drawable.ic_favorite_white)
            menu.getItem(menuItemId).setTitle(R.string.programme_top_nav_white_text)
        }
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.is_favor -> {
                if(item.title.toString() == getString(R.string.programme_top_nav_black_text)){
                    viewModel.currentProgrammeLiveData.value!!.isFavourite = true
                    menu.getItem(menuItemId).setIcon(R.drawable.ic_favorite_white)
                    menu.getItem(menuItemId).setTitle(R.string.programme_top_nav_white_text)
                }
                else  {
                    menu.getItem(menuItemId).setIcon(R.drawable.ic_favorite_black)
                    menu.getItem(menuItemId).setTitle(R.string.programme_top_nav_black_text)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}