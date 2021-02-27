package com.ben.myshoppinglist.view

import android.net.http.HttpResponseCache
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ben.myshoppinglist.R
import java.io.File
import com.ben.myshoppinglist.utils.TAG
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onSetupCache()
        onSetupNav()
    }

    override fun onStop() {
        super.onStop()
        val cache = HttpResponseCache.getInstalled()
        cache?.let {
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

    private fun onSetupNav() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navigationController = findNavController(R.id.nav_host_fragment_container)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.fragmentOne, R.id.fragmentTwo))
        setupActionBarWithNavController(navigationController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navigationController)
    }
}