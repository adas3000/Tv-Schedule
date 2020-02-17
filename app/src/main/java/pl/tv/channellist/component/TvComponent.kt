package pl.tv.channellist.component

import dagger.Component
import pl.tv.channellist.module.TvModule
import pl.tv.channellist.view.ui.MainActivity
import pl.tv.channellist.view.ui.ProgrammeFragment

@Component(modules = [TvModule::class])
interface TvComponent {

    fun inject(fragment: ProgrammeFragment)
}