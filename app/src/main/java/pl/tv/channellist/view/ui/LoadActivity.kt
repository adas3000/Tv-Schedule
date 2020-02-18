package pl.tv.channellist.view.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.tv.channellist.R
import pl.tv.channellist.component.DaggerTvComponent
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.model.repository.ProgrammeRepository
import javax.inject.Inject

class LoadActivity : AppCompatActivity() {

    companion object{
        const val TAG="LoadActivity"
    }

    @Inject
    lateinit var repository: ProgrammeRepository

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        DaggerTvComponent.builder().build().inject(this)


        if(savedInstanceState == null) setData()
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

            }

            override fun onError(e: Throwable) {
                Log.d(TAG, e.message.toString())
                setContentView(R.layout.fragment_load_error)

            }
        })
    }

}
