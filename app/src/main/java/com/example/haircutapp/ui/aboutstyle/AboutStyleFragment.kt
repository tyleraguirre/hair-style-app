package com.example.haircutapp.ui.aboutstyle

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
import com.example.haircutapp.databinding.AboutStyleBinding
import com.example.haircutapp.databinding.HairstyleImagesBinding
import com.example.haircutapp.ui.detail.DetailFragmentArgs

class AboutStyleFragment: Fragment() {

    private lateinit var binding: AboutStyleBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.about_style, container, false)

        webviewSetup()

        return binding.root
    }

    private fun webviewSetup() {
        binding.aboutStyleWebview.webViewClient = WebViewClient()
        binding.aboutStyleWebview.settings.javaScriptEnabled = true
        binding.aboutStyleWebview.settings.loadWithOverviewMode = true
        binding.aboutStyleWebview.settings.useWideViewPort = true

        binding.aboutStyleWebview.apply {
            loadUrl(sharedViewModel.selectedStyle.value?.aboutStyle!!)
        }
    }
}