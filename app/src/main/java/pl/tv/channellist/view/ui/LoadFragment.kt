package pl.tv.channellist.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.*
import pl.tv.channellist.R
import pl.tv.channellist.component.DaggerTvComponent
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.model.repository.ProgrammeRepository
import javax.inject.Inject

class LoadFragment : Fragment() {

    @Inject
    lateinit var repository: ProgrammeRepository
    private val compositeDisposable = CompositeDisposable()
    private lateinit var navController: NavController
    private lateinit var programmeList:List<TvProgramme>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_load,container,false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerTvComponent.builder().build().inject(this)
        navController = Navigation.findNavController(view)
        setData()
    }

    private fun setData(){
        val observableTvProgramme =
                Observable.create(ObservableOnSubscribe<List<TvProgramme>> {
                    if (!it.isDisposed) {
                        it.onNext(repository.doFindProgrammes())
                        it.onComplete()
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())

        observableTvProgramme.subscribe(object : io.reactivex.Observer<List<TvProgramme>> {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onComplete() {
                activity?.finish()
                startActivity(Intent(context,MainActivity::class.java))
            }

            override fun onNext(t: List<TvProgramme>) {
                programmeList = t
            }

            override fun onError(e: Throwable) {
                Log.d(LoadActivity.TAG, e.message.toString())
                navController.navigate(R.id.action_loadFragment_to_loadErrorFragment)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}