package by.dev.mo.ankoapplication.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import by.dev.mo.ankoapplication.ui.MainActivityUi
import by.dev.mo.ankoapplication.ui.inflateMenu
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {
    val ui: MainActivityUi by lazy {
        MainActivityUi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = menu?.run {
        inflateMenu(this@MainActivity, ui)
    } ?: super.onCreateOptionsMenu(menu)
}



