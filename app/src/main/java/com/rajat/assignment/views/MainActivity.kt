package com.rajat.assignment.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rajat.assignment.R
import com.rajat.assignment.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Utils.registerNetworkChangeReceiver(this);
    }

    override fun onDestroy() {
        super.onDestroy()
        Utils.unregisterNetworkChangeReceiver(this)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }


}