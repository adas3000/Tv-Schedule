package pl.tv.channellist.view.ui.util

import pl.tv.channellist.model.data.PendingProgramme

interface IProgramme {
    fun onProgrammeClick(movieList: List<PendingProgramme>)
}