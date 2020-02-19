package pl.tv.channellist.view.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import pl.tv.channellist.R
import pl.tv.channellist.component.DaggerTvComponent
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.model.repository.ProgrammeRepository
import pl.tv.channellist.viewmodel.ProgrammeViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
        const val sharedPreferencesName = "data"
    }

    private lateinit var viewModel: ProgrammeViewModel

    private val compositeDisposable = CompositeDisposable()

    private lateinit var sharedPreferences:SharedPreferences

    @Inject
    lateinit var repository: ProgrammeRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.fragment_container)

        DaggerTvComponent.builder().build().inject(this)

        viewModel = ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)

        NavigationUI.setupWithNavController(bottom_navigation_view, navController)

        sharedPreferences = this.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE) as SharedPreferences

        swipe_refresh_layout.setOnRefreshListener {
            viewModel.saveIsFavorValuesToSharedPreferences(sharedPreferences)
            setData()
            Handler().postDelayed({
                swipe_refresh_layout.isRefreshing = false
            }, 1000)
        }


    if(savedInstanceState == null){
        val putArrayObservable =
        Observable
                .fromIterable(intent.extras?.getParcelableArrayList<TvProgramme>("data"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())


        val programmeList:MutableList<TvProgramme> = mutableListOf()

        putArrayObservable.subscribe(object:Observer<TvProgramme>{
            override fun onComplete() {
                viewModel.setProgrammesAndRefreshFavors(programmeList,sharedPreferences)
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: TvProgramme) {
                programmeList.add(t)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"Error:"+e.message.toString())
            }
        })
    }
    }


    private fun setData() {
        val observableTvProgramme =
                Observable.create(ObservableOnSubscribe<List<TvProgramme>> {
                    if (!it.isDisposed) {
                        it.onNext(repository.doFindProgrammes())
                        it.onComplete()
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())

        observableTvProgramme.subscribe(object : Observer<List<TvProgramme>> {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onComplete() {

            }

            override fun onNext(t: List<TvProgramme>) {
                viewModel.setProgrammesAndRefreshFavors(t,sharedPreferences)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, e.message.toString())
                setData()
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.saveIsFavorValuesToSharedPreferences(sharedPreferences)
        compositeDisposable.clear()
    }


}
