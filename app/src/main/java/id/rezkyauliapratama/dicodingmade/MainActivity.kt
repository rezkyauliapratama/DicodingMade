package id.rezkyauliapratama.dicodingmade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.innovation.libnavigation.Actions
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.e("MainActivity")
        startActivity(Actions.openHomeIntent(this))
        finish()
    }

}
