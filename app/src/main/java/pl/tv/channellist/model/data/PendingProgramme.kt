package pl.tv.channellist.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PendingProgramme(
    val hour:String,
    var programmeName:String,
    val programmeCategory:String
):Parcelable