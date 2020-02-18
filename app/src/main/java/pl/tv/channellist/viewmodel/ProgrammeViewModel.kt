package pl.tv.channellist.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.tv.channellist.model.data.TvProgramme

class ProgrammeViewModel : ViewModel() {


    var programmeLiveData:MutableLiveData<List<TvProgramme>> = MutableLiveData()
    var currentProgrammeLiveData:MutableLiveData<TvProgramme> = MutableLiveData()

    init{
        programmeLiveData.value = listOf()
        currentProgrammeLiveData.value = TvProgramme("","", emptyList())
    }


    fun setProgrammes(tvProgrammes:List<TvProgramme>){
        programmeLiveData.value = tvProgrammes
    }


    fun setCurrentProgramme(programme: TvProgramme){
        currentProgrammeLiveData.value = programme
    }

    fun saveIsFavorValuesToSharedPreferences(sharedPreferences: SharedPreferences){
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        programmeLiveData.value?.forEach {
            editor.putBoolean(it.name,it.isFavourite)
        }
        editor.apply()
    }

    fun readIsFavorFromSharedPreferences(sharedPreferences: SharedPreferences){

        programmeLiveData.value?.forEach{
            it.isFavourite = sharedPreferences.getBoolean(it.name,false)
        }

    }
}