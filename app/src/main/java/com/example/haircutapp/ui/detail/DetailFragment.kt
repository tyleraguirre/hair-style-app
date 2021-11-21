package com.example.haircutapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.haircutapp.R
import com.example.haircutapp.SharedViewModel
import com.example.haircutapp.databinding.FragmentDetailBinding
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.hairstylesdatabase.HairstyleDao
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import com.example.haircutapp.ui.favorites.FavoritesAdapter
import com.example.haircutapp.ui.favorites.FavoritesFragment
import com.example.haircutapp.util.isBlackedOut
import com.example.haircutapp.util.isNormalState
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = HairstyleDatabase.getInstance(application).HairstyleDao

        binding.lifecycleOwner = this



        setUpView()

        binding.aboutThisStyleButton.setOnClickListener {
            //Need to pass aboutStyle value from FB database/Json based on what Hairstyle is showing on the detail fragment
//            openWebPage(args.hairstyle.aboutStyle)
            Navigation.findNavController(this.about_this_style_button).navigate(DetailFragmentDirections.
            actionDetailFragmentToAboutStyleFragment(args.hairstyle.aboutStyle))
        }

        binding.styleImagesButton.setOnClickListener {
            //Need to pass imagesOfStyle value from FB database/Json based on what Hairstyle is showing on the detail fragment
//            openWebPage(args.hairstyle.imagesOfStyle)
            Navigation.findNavController(this.style_images_button).navigate(DetailFragmentDirections.
            actionDetailFragmentToStyleImagesFragment(args.hairstyle.imagesOfStyle))
        }

        binding.addToFavoritesButton.setOnClickListener {
            //need to have style get added to favorites fragment
            //show a toast that the style has been added to fragment
            //need to hide button once style has been favorited
            Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
            sharedViewModel.updateHairstyle()
            if (sharedViewModel.isFavorited()) {
                binding.addToFavoritesButton.isBlackedOut()
            } else {
                binding.addToFavoritesButton.isNormalState()
//
            }
        }
        return binding.root
    }

    //This launches a Intent to open a web browser
    private fun openWebPage(url: String) {
        val webPage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        activity?.startActivity(intent)
    }

    fun setUpView() {
        binding.detailStyleName.text = args.hairstyle.styleName
        binding.detailStyleImage.setImageResource(args.hairstyle.styleImage!!)
    }

    override fun onResume() {
        super.onResume()
        if (sharedViewModel.isFavorited()) {
            binding.addToFavoritesButton.isBlackedOut()
        } else {
            binding.addToFavoritesButton.isNormalState()
        }

    }
}


//        sharedViewModel.selectedStyle.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                binding.detailStyleName.text = it.styleName
//                if (it.styleImage != null) {
//                    binding.detailStyleImage.setImageResource(it.styleImage!!)
//                }
//                aboutHairstylesUrl = it.aboutStyle
//                imagesOfHairstyleUrl = it.imagesOfStyle
//            }
//        })


