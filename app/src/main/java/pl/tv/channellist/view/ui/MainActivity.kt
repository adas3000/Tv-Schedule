package pl.tv.channellist.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
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
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: ProgrammeViewModel
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var repository: ProgrammeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerTvComponent.builder().build().inject(this)

        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ProgrammeViewModel::class.java)
        viewModel.repository = repository

        bottom_navigation_view.setOnNavigationItemSelectedListener {

            val selectedFragment: Fragment = when(it.itemId){
                R.id.nav_live-> LiveFragment()
                R.id.nav_channels->ProgrammeFragment()
                R.id.nav_favors->FavorsFragment()
                else -> {ProgrammeFragment()}
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,selectedFragment)
                .addToBackStack("")
                .commit()

            true
        }


        val observableTvProgramme =
            Observable.create(ObservableOnSubscribe<List<TvProgramme>> {
            if(!it.isDisposed){
                it.onNext(repository.doFindProgrammes())
                it.onComplete()
            }
        })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


        observableTvProgramme.subscribe(object: Observer<List<TvProgramme>>{
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onComplete() {

            }

            override fun onNext(t: List<TvProgramme>) {
                viewModel.setProgrammes(t)
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_SHORT).show()
            }
        })


    }



}
