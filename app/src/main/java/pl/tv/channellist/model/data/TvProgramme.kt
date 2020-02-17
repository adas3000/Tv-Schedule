package pl.tv.channellist.model.data

data class TvProgramme(
    val name:String,
    val logoUrl:String,
    val movieList:List<PendingProgramme>,
    var isFavourite :Boolean = false
)