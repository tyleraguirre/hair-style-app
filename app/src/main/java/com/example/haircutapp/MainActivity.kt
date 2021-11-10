package com.example.haircutapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.haircutapp.databinding.ActivityMainBinding
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkFirstRun()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavview

        navController = findNavController(R.id.nav_host_fragment_activity_main)
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
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun checkFirstRun() {
        val sharedPreferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        val firstRun = sharedPreferences.getBoolean("first_run", true)

        if (firstRun) {
            lifecycleScope.launch {
                forceDataBaseInit()
            }
            sharedPreferences.edit().putBoolean("first_run", false).apply()
        }
    }
    private fun forceDataBaseInit() {
        var db = HairstyleDatabase.getInstance(this)
        lifecycleScope.launch {
            db.HairstyleDao.insert(StyleList.styleList)
        }
    }
}


    object StyleList {
        val styleList = listOf<Hairstyle>(
            Hairstyle(
                1,
                "broflow",
                0,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Wings_(haircut)",
                "https://www.pinterest.com/bartogilvie/bro-flow-hairstyles-men/"
            ),
            Hairstyle(
                2,
                "buzzcut",
                0,
                R.drawable.buzzcut_sv_m,
                "https://en.wikipedia.org/wiki/Buzz_cut",
                "https://www.pinterest.com/mmanderi/buzz-cut/"
            ),
            Hairstyle(
                3,
                "caesarcut",
                0,
                R.drawable.ceasarcut,
                "https://en.wikipedia.org/wiki/Caesar_cut",
                "https://www.pinterest.com/menshairtips/caesar-haircut/"
            ),
            Hairstyle(
                4,
                "combover",
                0,
                R.drawable.combover,
                "https://en.wikipedia.org/wiki/Comb_over",
                "https://www.pinterest.com/menshairtips/comb-over-hairstyle/"
            ),
            Hairstyle(
                5,
                "crewcut",
                0,
                R.drawable.crewcutxxhdpi,
                "https://en.wikipedia.org/wiki/Crew_cut",
                "https://www.pinterest.com/menshairtips/crew-cut-haircut/"
            ),
            Hairstyle(
                6,
                "fade",
                0,
                R.drawable.fadexxhdpi,
                "https://en.wikipedia.org/wiki/Regular_haircut",
                "https://www.pinterest.com/menshairtoday/fade-haircuts/"
            ),
            Hairstyle(
                7,
                "fauxhawk",
                0,
                R.drawable.fauxhawk_sv_m,
                "https://en.wikipedia.org/wiki/Mohawk_hairstyle",
                "https://www.pinterest.com/menshairtips/faux-hawk-hairstyle/"
            ),
            Hairstyle(
                8,
                "fringe",
                0,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Bangs_(hair)",
                "https://www.pinterest.com/silva0966/mens-fringe/"
            ),
            Hairstyle(
                9,
                "manbun",
                0,
                R.drawable.manbun_sv_m,
                "https://en.wikipedia.org/wiki/Bun_(hairstyle)",
                "https://www.pinterest.com/genggam_pw/manbun/"
            ),
            Hairstyle(
                10,
                "pompadour",
                0,
                R.drawable.pompadour_sv_m,
                "https://en.wikipedia.org/wiki/Pompadour_(hairstyle)",
                "https://www.pinterest.com/barbinccom/mens-pompadours/"
            ),
            Hairstyle(
                11,
                "quiff",
                0,
                R.drawable.quiff_sv_m,
                "https://en.wikipedia.org/wiki/Quiff",
                "https://www.pinterest.com/tath7788/quiff/"
            ),
            Hairstyle(
                12,
                "topknot",
                0,
                R.drawable.topknotxxhdpi,
                "https://en.wikipedia.org/wiki/Topknot",
                "https://www.pinterest.com/tgo_eden/mens-top-knotman-bun/"
            ),
            Hairstyle(
                13,
                "undercut",
                0,
                R.drawable.undercutxxhdpi,
                "https://en.wikipedia.org/wiki/Undercut_(hairstyle)",
                "https://www.pinterest.com/menshairtoday/undercut-hairstyles-for-men/"
            )
        )
}

