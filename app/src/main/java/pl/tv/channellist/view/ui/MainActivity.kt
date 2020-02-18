package pl.tv.channellist.view.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
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

    companion object{
        const val TAG = "MainActivity"
    }

    private lateinit var viewModel: ProgrammeViewModel

    private val compositeDisposable = CompositeDisposable()
    @Inject
    lateinit var repository: ProgrammeRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this,R.id.fragment_container)

        DaggerTvComponent.builder().build().inject(this)
        viewModel = ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)

        NavigationUI.setupWithNavController(bottom_navigation_view,navController)


        swipe_refresh_layout.setOnRefreshListener {
            if(supportFragmentManager.findFragmentById(R.id.fragment_container) is ProgrammeFragment) {
                setData()
                val handler = Handler()
                handler.postDelayed({
                    swipe_refresh_layout.isRefreshing = false
                }, 1000)
            }
            else swipe_refresh_layout.isRefreshing = false
        }

        if(savedInstanceState==null)
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

            }

            override fun onNext(t: List<TvProgramme>) {
                viewModel.setProgrammes(t)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, e.message.toString())
                setData()
            }
        })
    }



    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


    private fun readFromFile():List<String>{

        val inputStream = openFileInput(getString(R.string.is_favor_txt_file_name_text))

        if(inputStream != null){
            val inputStreamReader = InputStreamReader(inputStream)

            return BufferedReader(inputStreamReader).readLines()
        }
        return emptyList()
    }



}
