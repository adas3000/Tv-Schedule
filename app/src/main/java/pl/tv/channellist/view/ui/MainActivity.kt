package pl.tv.channellist.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pl.tv.channellist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_view.setOnNavigationItemSelectedListener {

//            val selectedFragment:Fragment
//
//            when(it.itemId){
//                R.id.nav_tv->
//            }



            true
        }
    }



}
