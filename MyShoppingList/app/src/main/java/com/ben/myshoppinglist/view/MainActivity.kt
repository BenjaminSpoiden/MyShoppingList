package com.ben.myshoppinglist.view

import android.content.Intent
import android.net.http.HttpResponseCache
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ben.myshoppinglist.R
import com.ben.myshoppinglist.model.UserPreferences
import com.ben.myshoppinglist.utils.TAG
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPreferences = UserPreferences(this)

        userPreferences.accessTokenFlow.asLiveData().observe(this) {
            Toast.makeText(this,it ?: "Token is null", Toast.LENGTH_SHORT).show()
        }
        onSetupCache()
//        onSetupNav()
    }

    override fun onStop() {
        super.onStop()
        val cache = HttpResponseCache.getInstalled()
        cache.let {
            cache.flush()
        }
    }

    private fun onSetupCache() {
        try {
            val httpCacheDir = File(applicationContext.cacheDir, "http")
            val cacheSize = (10 * 1024 * 1024).toLong()
            HttpResponseCache.install(httpCacheDir, cacheSize)
            Log.d(TAG, "Successfully setup cache")
        } catch (e: Exception) {
            Log.d(TAG, "Exception: $e")
        }
    }

//    private fun onSetupNav() {
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        val navigationController = findNavController(R.id.nav_host_fragment_container)
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.fragmentOne, R.id.fragmentTwo))
//        setupActionBarWithNavController(navigationController, appBarConfiguration)
//
//        bottomNavigationView.setupWithNavController(navigationController)
//    }
}