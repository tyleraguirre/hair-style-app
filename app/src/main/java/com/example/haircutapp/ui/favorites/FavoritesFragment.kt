package com.example.haircutapp.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haircutapp.R
import com.example.haircutapp.SharedViewModel
import com.example.haircutapp.databinding.FragmentFavoritesBinding
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.util.fadeInText
import com.example.haircutapp.util.isBlackedOut
import com.example.haircutapp.util.isNormalState
import com.example.haircutapp.util.logd
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val manager = GridLayoutManager(activity, 3)

        val adapter = FavoritesAdapter(sharedViewModel)



        binding.favoritesRecyclerview.fadeInText()
        binding.favoritesText.fadeInText()
        binding.clearbutton.fadeInText()

        sharedViewModel.hairstylesList.observe(viewLifecycleOwner, Observer {
            it?.let { hairstyleslist ->
                val filteredHairstyles = hairstyleslist.filter { hairstyle ->
                    hairstyle.favorited == 1
                }

                adapter.submitList(filteredHairstyles)

                println("$filteredHairstyles")
            }

        })

/* Here I need this button onClicked to remove all favorited/saved hairstyles from this
Favorited Fragment and then disable the button but at the moment it will only remove 1
and then disable the button
 */
        binding.clearbutton.setOnClickListener {
            // here we call our new function
            sharedViewModel.removeAllFavorited() { isEmpty ->
                if (isEmpty) {
                    binding.clearbutton.isBlackedOut()
                } else {
                    binding.clearbutton.isNormalState()
                }
            }

        }


        binding.favoritesRecyclerview.layoutManager = manager

        binding.favoritesRecyclerview.adapter = adapter

        return binding.root
    }


    // When the fragment resumes we will check the state of the button
    override fun onResume() {
        super.onResume()
        if (sharedViewModel.favoritesEmpty()) {
            binding.clearbutton.isBlackedOut()
        } else {
            binding.clearbutton.isNormalState()
        }
    }

}