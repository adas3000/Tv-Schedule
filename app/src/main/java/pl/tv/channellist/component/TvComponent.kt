package pl.tv.channellist.component

import dagger.Component
import pl.tv.channellist.module.TvModule
import pl.tv.channellist.view.ui.MainActivity

@Component(modules = [TvModule::class])
interface TvComponent {

    fun inject(mainActivity: MainActivity)
}