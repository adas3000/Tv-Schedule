package pl.tv.channellist.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.tv.channellist.R


class LoadActivity : AppCompatActivity() {

    companion object{
        const val TAG="LoadActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
    }



}
