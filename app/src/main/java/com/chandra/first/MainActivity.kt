package com.chandra.first

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.chandra.first.module.login.LoginFragment
import com.chandra.first.module.main.MainFragment
import com.chandra.first.util.toast

class MainActivity : AppCompatActivity() {

    private var backPressed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onBackPressed() {
        findNavController(R.id.nav_host_fragment)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val current = navHost?.childFragmentManager?.fragments?.get(0)

        if (current is LoginFragment || (current is MainFragment && backPressed == 1)) {
            finish()
        } else if (current is MainFragment && backPressed == 0) {
            backPressed += 1
            toast("Press back again to exit")
            Handler(Looper.getMainLooper()).postDelayed({
                backPressed = 0
            }, 2000)
        } else {
            super.onBackPressed()
        }
    }
}