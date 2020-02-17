package pl.tv.channellist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.tv.channellist.model.data.TvProgramme

class ProgrammeViewModel : ViewModel() {


    var programmeLiveData:MutableLiveData<List<TvProgramme>> = MutableLiveData()
    var currentProgrammeLiveData:MutableLiveData<TvProgramme> = MutableLiveData()

    fun setProgrammes(tvProgrammes:List<TvProgramme>){
        programmeLiveData.value = tvProgrammes
    }


    fun setCurrentProgramme(programme: TvProgramme){
        currentProgrammeLiveData.value = programme
    }

}