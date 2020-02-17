package pl.tv.channellist.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.android.synthetic.main.fragment_programmes.*
import pl.tv.channellist.R
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.view.adapter.ProgrammeAdapter
import pl.tv.channellist.view.ui.util.IProgramme
import pl.tv.channellist.viewmodel.ProgrammeViewModel

class FavorsFragment : Fragment(),IProgramme {


    private lateinit var viewModel: ProgrammeViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_programmes,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ProgrammeViewModel::class.java)

        val favorProgrammesList :MutableList<TvProgramme> = mutableListOf()

        val favorProgrammesObservable = Observable
            .fromIterable(viewModel.programmeLiveData.value as List<TvProgramme>)
            .filter { it.isFavourite }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

        favorProgrammesObservable.subscribe(object:Observer<TvProgramme>{
            override fun onComplete() {
                recyclerView_programmes.adapter = ProgrammeAdapter(favorProgrammesList,this@FavorsFragment)
                recyclerView_programmes.layoutManager = LinearLayoutManager(context)
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.addAll(d)
            }

            override fun onNext(t: TvProgramme) {
                favorProgrammesList.add(t)
            }

            override fun onError(e: Throwable) {
                Log.d(MainActivity.TAG,e.message.toString())
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

    override fun onProgrammeClick(clickedProgramme: TvProgramme) {
        viewModel.setCurrentProgramme(clickedProgramme)

        fragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container,MovieFragment())
            .addToBackStack("")
            .commit()
    }


}