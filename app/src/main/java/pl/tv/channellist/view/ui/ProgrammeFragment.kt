package pl.tv.channellist.view.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_programmes.*
import pl.tv.channellist.R
import pl.tv.channellist.component.DaggerTvComponent
import pl.tv.channellist.model.data.PendingProgramme
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.model.repository.ProgrammeRepository
import pl.tv.channellist.view.adapter.ProgrammeAdapter
import pl.tv.channellist.view.ui.util.IProgramme
import pl.tv.channellist.viewmodel.ProgrammeViewModel
import javax.inject.Inject

class ProgrammeFragment :Fragment(),IProgramme {


    private lateinit var viewModel:ProgrammeViewModel
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var repository: ProgrammeRepository


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_programmes,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val adapter = ProgrammeAdapter(listOf(),this)
        recyclerView_programmes.adapter = adapter
        recyclerView_programmes.layoutManager = LinearLayoutManager(context)


        viewModel.programmeLiveData.observe(this,Observer<List<TvProgramme>>{
            adapter.setProgrammeList(it)
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ProgrammeViewModel::class.java)

        DaggerTvComponent.builder().build().inject(this)

        val observableTvProgramme =
            Observable.create(ObservableOnSubscribe<List<TvProgramme>> {
                if(!it.isDisposed){
                    it.onNext(repository.doFindProgrammes())
                    it.onComplete()
                }
            })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

        observableTvProgramme.subscribe(object: io.reactivex.Observer<List<TvProgramme>> {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onComplete() {

            }

            override fun onNext(t: List<TvProgramme>) {
                viewModel.setProgrammes(t)
            }

            override fun onError(e: Throwable) {
                Log.d(MainActivity.TAG,e.message.toString())
            }
        })

    }


    override fun onProgrammeClick(clickedProgramme: TvProgramme) {

        viewModel.setCurrentProgramme(clickedProgramme)

        fragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container,MovieFragment())
            .addToBackStack("")
            .commit()
    }

}