package com.example.haircutapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.haircutapp.databinding.ActivityMainBinding
import com.example.haircutapp.gson.RetroFitInstance
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.hairstylesdatabase.HairstyleDao
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    companion object {
        const val TAG = "MainActivity"
    }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        getDatabase()

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

//        readData()
        fetchDataAndStore()

    }
    fun fetchDataAndStore() {
        val styleList = listOf("broflow","buzzcut","caesarcut","combover","crewcut","fade","fauxhawk","fringe","manbun","pompadour",
        "quiff","topknot","undercut")

        database = FirebaseDatabase.getInstance("https://hairstyle-api-e5fc7-default-rtdb.firebaseio.com/").getReference("hairstyles")

        var hairstylesList = mutableListOf<Hairstyle>()

        for (style in styleList) {
            database.child("$style").get().addOnSuccessListener { data ->
                var keys = data.key
                data.value
                data.toString()
                keys.toString()
                Log.i("TEST","$data")
               val myObject = data.getValue(Hairstyle::class.java)
                Log.i("$TAG", "this is the $myObject")
            }
        }
        forceDatabaseInit()
    }


    fun forceDatabaseInit() {
        val db = HairstyleDatabase.getInstance(this)

//        val data = readData()

        lifecycleScope.launch {
//            db.HairstyleDao.insert(data)
        }
        }
    }