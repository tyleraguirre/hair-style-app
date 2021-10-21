package com.example.haircutapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.haircutapp.databinding.ActivityMainBinding
import com.example.haircutapp.gson.Repository
import com.example.haircutapp.gson.RetroFitInstance
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDatabase()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavview

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_styles,
                R.id.navigation_favorites,
                R.id.navigation_map
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // CSV functionality
        val inputStream: InputStream = resources.openRawResource(R.raw.stylesdb)
        val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
        reader.readLines().forEach {
            //get a string array of all items in this line
            val items = it.split(",")
            //do what you want with each item
            Log.i("stylesDB", items.toString())
        }

    }


    fun getDatabase() {
//        val sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
//        val firstRun = sharedPrefs.getBoolean("first_run", true)

            lifecycleScope.launch {
                val repository = Repository()

                val response = repository.getStyles()

                val test = RetroFitInstance.readData()


                Log.i("$TAG", "${test}")
//                if (response.isSuccessful) {
//                    Log.i("styles", "styles is ${response.body()}")
//                    println("${response.body()}")
//                } else {
//                    Log.i("styles", "Not successful")
//                }
            }
//            sharedPrefs.edit().putBoolean("first_run", false).apply()
        }
    }