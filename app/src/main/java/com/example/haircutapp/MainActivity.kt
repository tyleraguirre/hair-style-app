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
                null,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Wings_(haircut)",
                "https://www.pinterest.com/bartogilvie/bro-flow-hairstyles-men/"
            ),
            Hairstyle(
                2,
                "buzzcut",
                null,
                R.drawable.buzzcut_sv_m,
                "https://en.wikipedia.org/wiki/Buzz_cut",
                "https://www.pinterest.com/mmanderi/buzz-cut/"
            ),
            Hairstyle(
                3,
                "caesarcut",
                null,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Caesar_cut",
                "https://www.pinterest.com/menshairtips/caesar-haircut/"
            ),
            Hairstyle(
                4,
                "combover",
                null,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Comb_over",
                "https://www.pinterest.com/menshairtips/comb-over-hairstyle/"
            ),
            Hairstyle(
                5,
                "crewcut",
                null,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Crew_cut",
                "https://www.pinterest.com/menshairtips/crew-cut-haircut/"
            ),
            Hairstyle(
                6,
                "fade",
                null,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Regular_haircut",
                "https://www.pinterest.com/menshairtoday/fade-haircuts/"
            ),
            Hairstyle(
                7,
                "fauxhawk",
                null,
                R.drawable.fauxhawk_sv_m,
                "https://en.wikipedia.org/wiki/Mohawk_hairstyle",
                "https://www.pinterest.com/menshairtips/faux-hawk-hairstyle/"
            ),
            Hairstyle(
                8,
                "fringe",
                null,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Bangs_(hair)",
                "https://www.pinterest.com/silva0966/mens-fringe/"
            ),
            Hairstyle(
                9,
                "manbun",
                null,
                R.drawable.manbun_sv_m,
                "https://en.wikipedia.org/wiki/Bun_(hairstyle)",
                "https://www.pinterest.com/genggam_pw/manbun/"
            ),
            Hairstyle(
                10,
                "pompadour",
                null,
                R.drawable.pompadour_sv_m,
                "https://en.wikipedia.org/wiki/Pompadour_(hairstyle)",
                "https://www.pinterest.com/barbinccom/mens-pompadours/"
            ),
            Hairstyle(
                11,
                "quiff",
                null,
                R.drawable.quiff_sv_m,
                "https://en.wikipedia.org/wiki/Quiff",
                "https://www.pinterest.com/tath7788/quiff/"
            ),
            Hairstyle(
                12,
                "topknot",
                null,
                R.drawable.manbun_sv_m,
                "https://en.wikipedia.org/wiki/Topknot",
                "https://www.pinterest.com/tgo_eden/mens-top-knotman-bun/"
            ),
            Hairstyle(
                13,
                "undercut",
                null,
                R.drawable.model_sv_m,
                "https://en.wikipedia.org/wiki/Undercut_(hairstyle)",
                "https://www.pinterest.com/menshairtoday/undercut-hairstyles-for-men/"
            )

//            "broflow", R.drawable.model_sv_m,
//            "buzzcut", R.drawable.buzzcut_sv_m,
//            "caesarcut", R.drawable.model_sv_m,
//            "combover", R.drawable.model_sv_m,
//            "crewcut", R.drawable.model_sv_m,
//            "fade", R.drawable.model_sv_m,
//            "fauxhawk", R.drawable.fauxhawk_sv_m,
//            "fringe", R.drawable.model_sv_m,
//            "manbun", R.drawable.manbun_sv_m,
//            "pompadour", R.drawable.pompadour_sv_m,
//            "quiff", R.drawable.quiff_sv_m,
//            "topknot", R.drawable.manbun_sv_m,
//            "undercut" R.drawable.model_sv_m,
        )
}

