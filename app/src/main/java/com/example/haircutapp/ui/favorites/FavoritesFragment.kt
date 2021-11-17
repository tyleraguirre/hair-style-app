package com.example.haircutapp.ui.favorites

import android.os.Bundle
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

        sharedViewModel.hairstylesList.observe(viewLifecycleOwner, Observer { hairstyleslist ->
            val filteredHairstyles = hairstyleslist.filter { hairstyle ->
                hairstyle.favorited == 1
            }
           adapter.submitList(filteredHairstyles)
        })

        binding.clearbutton.setOnClickListener {
          sharedViewModel.updateHairstyle()
        }

        binding.favoritesRecyclerview.layoutManager = manager

        binding.favoritesRecyclerview.adapter = adapter

        return binding.root
    }
}