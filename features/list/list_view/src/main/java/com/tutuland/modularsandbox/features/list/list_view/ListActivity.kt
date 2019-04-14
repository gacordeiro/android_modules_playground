package com.tutuland.modularsandbox.features.list.list_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutuland.modularsandbox.libraries.actions.Actions.openDetailsScreen
import kotlinx.android.synthetic.main.list_activity.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        btn_private.setOnClickListener { openDetailsScreen() }
    }
}
