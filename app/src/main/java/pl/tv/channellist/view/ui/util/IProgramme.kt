package pl.tv.channellist.view.ui.util

import pl.tv.channellist.model.data.PendingProgramme
import pl.tv.channellist.model.data.TvProgramme

interface IProgramme {
    fun onProgrammeClick(programmeList: List<PendingProgramme>)
}