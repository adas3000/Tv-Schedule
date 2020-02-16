package pl.tv.channellist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.tv.channellist.model.data.PendingProgramme
import pl.tv.channellist.model.data.TvProgramme
import pl.tv.channellist.model.repository.ProgrammeRepository

class ProgrammeViewModel : ViewModel() {


    lateinit var repository: ProgrammeRepository

    var programmeLiveData:MutableLiveData<List<TvProgramme>> = MutableLiveData()
    var movieLiveData:MutableLiveData<List<PendingProgramme>> = MutableLiveData()

    fun setProgrammes(tvProgrammes:List<TvProgramme>){
        programmeLiveData.value = tvProgrammes
    }

    fun setMovies(pendingProgramme: List<PendingProgramme>){
        movieLiveData.value = pendingProgramme
    }


}