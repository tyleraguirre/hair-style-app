package com.example.haircutapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.haircutapp.R
import com.example.haircutapp.SharedViewModel
import com.example.haircutapp.databinding.FragmentDetailBinding
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private var aboutHairstylesUrl = ""
    private var imagesOfHairstyleUrl = ""

    val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = HairstyleDatabase.getInstance(application).HairstyleDao
        
        binding.lifecycleOwner = this

        sharedViewModel.selectedStyle.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.detailStyleName.text = it.styleName
                if (it.styleImage != null) {
                    binding.detailStyleImage.setImageResource(it.styleImage!!)
                }
                aboutHairstylesUrl = it.aboutStyle
                imagesOfHairstyleUrl = it.imagesOfStyle

                sharedViewModel.navigationComplete()
            }
        })

        binding.aboutThisStyleButton.setOnClickListener {
            //Need to pass aboutStyle value from FB database/Json based on what Hairstyle is showing on the detail fragment
            openWebPage(aboutHairstylesUrl)
        }

        binding.styleImagesButton.setOnClickListener {
            //Need to pass imagesOfStyle value from FB database/Json based on what Hairstyle is showing on the detail fragment
            openWebPage(imagesOfHairstyleUrl)
        }

//        add_to_favorites_button.setOnClickListener {
//            //need to have style get added to favorites fragment
//
//            //show a toast that the style has been added to fragment
////            Toast
//        }

        return binding.root
    }
    //This launches a Intent to open a web browser
    private fun openWebPage(url: String) {
        val webPage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        activity?.startActivity(intent)
    }
}