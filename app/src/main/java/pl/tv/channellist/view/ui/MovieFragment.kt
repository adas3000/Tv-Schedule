package pl.tv.channellist.view.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.tv.channellist.R
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.view.adapter.MovieAdapter
import pl.tv.channellist.viewmodel.ProgrammeViewModel
import java.io.OutputStreamWriter

class MovieFragment : Fragment() {

    companion object{
        const val menuItemFavorId = 1
        const val menuItemIconId = 0
    }

    private lateinit var viewModel: ProgrammeViewModel
    private lateinit var menu:Menu
    private lateinit var programmeIconUrl:String


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
            programmeIconUrl = it.logoUrl
        })



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.channels_top_menu,menu)
        if(viewModel.currentProgrammeLiveData.value!!.isFavourite){
            menu.getItem(menuItemFavorId).setIcon(R.drawable.ic_favorite_white)
            menu.getItem(menuItemFavorId).setTitle(R.string.programme_top_nav_white_text)
        }
        this.menu = menu
        loadImg(menu,programmeIconUrl)
    }

    private fun loadImg(menu:Menu,urlPath:String){
        Glide.with(this)
            .asBitmap()
            .load(urlPath)
            .into(object:CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    menu[menuItemIconId].icon = BitmapDrawable(context?.resources,resource)
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.is_favor -> {
                if(item.title.toString() == getString(R.string.programme_top_nav_black_text)){
                    viewModel.currentProgrammeLiveData.value!!.isFavourite = true
                    menu.getItem(menuItemFavorId).setIcon(R.drawable.ic_favorite_white)
                    menu.getItem(menuItemFavorId).setTitle(R.string.programme_top_nav_white_text)
                }
                else  {
                    viewModel.currentProgrammeLiveData.value!!.isFavourite = false
                    menu.getItem(menuItemFavorId).setIcon(R.drawable.ic_favorite_black)
                    menu.getItem(menuItemFavorId).setTitle(R.string.programme_top_nav_black_text)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun writeToFile(data:List<String>){
        val outputStreamWriter = OutputStreamWriter(context!!.openFileOutput(getString(R.string.is_favor_txt_file_name_text), Context.MODE_PRIVATE))
        data.forEach(outputStreamWriter::write)
        outputStreamWriter.close()
    }

}