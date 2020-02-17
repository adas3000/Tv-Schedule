package pl.tv.channellist.view.ui

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
import kotlinx.android.synthetic.main.fragment_live.*
import pl.tv.channellist.R
import pl.tv.channellist.model.data.LiveTvProgramme
import pl.tv.channellist.view.adapter.LiveMovieAdapter
import pl.tv.channellist.viewmodel.ProgrammeViewModel

class LiveFragment : Fragment(){

    private lateinit var viewModel: ProgrammeViewModel
    private val liveProgrammeList:MutableList<LiveTvProgramme> = mutableListOf()

    private val compositeDisposable:CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_live,container,false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ProgrammeViewModel::class.java)

        val programmeLiveObservable = Observable
            .fromIterable(viewModel.programmeLiveData.value)
            .map {
                LiveTvProgramme(it.logoUrl,it.movieList[0],it.movieList[1].hour)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

        programmeLiveObservable.subscribe(object:Observer<LiveTvProgramme>{
            override fun onComplete() {
                recyclerView_live.adapter = LiveMovieAdapter(liveProgrammeList)
                recyclerView_live.layoutManager = LinearLayoutManager(context)
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: LiveTvProgramme) {
                liveProgrammeList.add(t)
            }

            override fun onError(e: Throwable) {
                Log.d(MainActivity.TAG,e.message.toString())
            }
        })


    }





}