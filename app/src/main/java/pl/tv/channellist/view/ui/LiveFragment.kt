package pl.tv.channellist.view.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.tv.channellist.R
import pl.tv.channellist.model.data.PendingProgramme
import pl.tv.channellist.view.adapter.MovieAdapter
import pl.tv.channellist.viewmodel.ProgrammeViewModel

class LiveFragment : Fragment(){

    private lateinit var viewModel: ProgrammeViewModel
    private val liveProgrammeList:MutableList<PendingProgramme> = mutableListOf()

    private val compositeDisposable:CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies,container,false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = MovieAdapter(listOf())
        recyclerView_movies.adapter = adapter
        recyclerView_movies.layoutManager = LinearLayoutManager(context)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ProgrammeViewModel::class.java)

        val programmeLiveObservable = Observable
            .fromIterable(viewModel.programmeLiveData.value)
            .map {
                it.movieList[0]
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

        programmeLiveObservable.subscribe(object:Observer<PendingProgramme>{
            override fun onComplete() {
                recyclerView_movies.adapter = MovieAdapter(liveProgrammeList)
                recyclerView_movies.layoutManager = LinearLayoutManager(context)
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: PendingProgramme) {
                liveProgrammeList.add(t)
            }

            override fun onError(e: Throwable) {
                Log.d(MainActivity.TAG,e.message.toString())
            }
        })



    }



}