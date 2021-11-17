package com.example.haircutapp.ui.hairstyleimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.haircutapp.R
import com.example.haircutapp.SharedViewModel
import com.example.haircutapp.databinding.FragmentFavoritesBinding
import com.example.haircutapp.databinding.HairstyleImagesBinding
import com.example.haircutapp.ui.detail.DetailFragmentArgs

class StyleImagesFragment: Fragment() {

    private lateinit var binding: HairstyleImagesBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.hairstyle_images, container, false)

       webviewSetup()

        return binding.root
    }

    private fun webviewSetup() {
        binding.styleImagesWebview.webViewClient = WebViewClient()
        binding.styleImagesWebview.settings.javaScriptEnabled = true
        binding.styleImagesWebview.settings.loadWithOverviewMode = true
        binding.styleImagesWebview.settings.useWideViewPort = true
        
        binding.styleImagesWebview.apply {
            loadUrl(sharedViewModel.selectedStyle.value?.imagesOfStyle!!)
        }
    }
}