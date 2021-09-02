package com.example.haircutapp

import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.haircutapp.databinding.ActivityMainBinding
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_styles, R.id.navigation_favorites, R.id.navigation_search, R.id.navigation_map
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        csvReader().open("stylesDatabaseAst.csv") {
//            readAllWithHeaderAsSequence().forEach { row: Map<String, String> ->
//                //Do something
//                println(row) //{id=1, name=doyaaaaaken}
//                Log.i("test2", "${row}")
//            }
//        }

        val readTxt = csvReader().open("src/main/assets/stylesDatabaseAst.csv") {
            readAllAsSequence().forEach { row ->
                println(row)
                Log.v("styleDB", "${row}")
            }
        }
        println(readTxt)

        try {
            val readTxt = csvReader().open("src/main/res/styles_database_res.csv") {
                readAllAsSequence().forEach { row ->
                    println(row)
                    Log.i("styleDB", "${row}")
                }
            }
        } catch (exception: NumberFormatException) {
            println(exception)
        }



    }
}