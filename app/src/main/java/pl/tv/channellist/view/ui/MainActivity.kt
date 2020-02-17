package pl.tv.channellist.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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


        DaggerTvComponent.builder().build().inject(this)
        viewModel = ViewModelProviders.of(this).get(ProgrammeViewModel::class.java)

        bottom_navigation_view.setOnNavigationItemSelectedListener {

            val selectedFragment: Fragment = when (it.itemId) {
                R.id.nav_live -> LiveFragment()
                R.id.nav_channels -> ProgrammeFragment()
                R.id.nav_favors -> FavorsFragment()
                else -> {
                    ProgrammeFragment()
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .addToBackStack("")
                .commit()

            true
        }

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
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProgrammeFragment())
                    .commit()
            }

            override fun onNext(t: List<TvProgramme>) {
                viewModel.setProgrammes(t)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, e.message.toString())
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


}
