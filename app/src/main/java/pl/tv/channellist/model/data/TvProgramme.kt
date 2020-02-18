package pl.tv.channellist.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvProgramme(
    val name:String,
    val logoUrl:String,
    val movieList:List<PendingProgramme>,
    var isFavourite :Boolean = false
):Parcelable