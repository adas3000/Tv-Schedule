package pl.tv.channellist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.tv.channellist.model.data.PendingProgramme
import pl.tv.channellist.model.data.TvProgramme

class ProgrammeViewModel : ViewModel() {


    var programmeLiveData:MutableLiveData<List<TvProgramme>> = MutableLiveData()
    var movieLiveData:MutableLiveData<List<PendingProgramme>> = MutableLiveData()
    var currentProgrammeLiveData:MutableLiveData<TvProgramme> = MutableLiveData()

    fun setProgrammes(tvProgrammes:List<TvProgramme>){
        programmeLiveData.value = tvProgrammes
    }

    fun setMovies(pendingProgramme: List<PendingProgramme>){
        movieLiveData.value = pendingProgramme
    }

    fun setCurrentProgramme(programme: TvProgramme){
        currentProgrammeLiveData.value = programme
    }

}