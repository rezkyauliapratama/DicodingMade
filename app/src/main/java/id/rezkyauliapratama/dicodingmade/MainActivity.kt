package id.rezkyauliapratama.dicodingmade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.innovation.libnavigation.Actions
import id.innovation.libnavigation.Activities
import id.innovation.libnavigation.intentTo
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = intentTo(this, addressableActivity = Activities.Home)
        startActivity(intent)
        finish()
    }

}
