package com.example.haircutapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haircutapp.R
import com.example.haircutapp.SharedViewModel
import com.example.haircutapp.StylesAdapter
import com.example.haircutapp.databinding.FragmentFavoritesBinding
import com.example.haircutapp.ui.styles.StylesFragmentDirections

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val application = requireNotNull(this.activity).application
        val manager = GridLayoutManager(activity, 3)

        val adapter = FavoritesAdapter(sharedViewModel)

        sharedViewModel.selectedStyle.observe(viewLifecycleOwner, Observer {
            it?.let { hairstyle ->
                // Navigate to detail fragment
                this.findNavController().navigate(
                    StylesFragmentDirections.actionNavigationStylesToDetailFragment(hairstyle))
            }
        })

        sharedViewModel.hairstylesList.observe(viewLifecycleOwner, Observer { hairstyleList ->
            adapter.submitList(hairstyleList)
        })

        return binding.root
    }
}