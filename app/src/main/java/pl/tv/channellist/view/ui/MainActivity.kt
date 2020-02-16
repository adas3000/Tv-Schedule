package pl.tv.channellist.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import pl.tv.channellist.R
import pl.tv.channellist.model.repository.ProgrammeRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var programmeRepo:ProgrammeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        bottom_navigation_view.setOnNavigationItemSelectedListener {

            val selectedFragment: Fragment = when(it.itemId){
                R.id.nav_live-> LiveFragment()
                R.id.nav_channels->ProgrammeFragment()
                R.id.nav_favors->FavorsFragment()
                else -> {ProgrammeFragment()}
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,selectedFragment)
                .addToBackStack("")
                .commit()

            true
        }
    }



}
