package com.tutuland.modularsandbox.apps.blue

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutuland.modularsandbox.libraries.actions.Actions.openListScreen
import kotlinx.android.synthetic.main.start_activity.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        btn_public.setOnClickListener { startActivity(Intent("com.tutuland.modularsandbox.features.list.open")) }
        btn_private.setOnClickListener { openListScreen() }
    }
}
