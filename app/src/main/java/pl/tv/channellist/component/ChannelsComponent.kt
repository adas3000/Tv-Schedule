package pl.tv.channellist.component

import dagger.Component
import pl.tv.channellist.module.ChannelsModule
import pl.tv.channellist.view.ui.MainActivity

@Component(modules = [ChannelsModule::class])
interface ChannelsComponent {

    fun inject(mainActivity: MainActivity)
}