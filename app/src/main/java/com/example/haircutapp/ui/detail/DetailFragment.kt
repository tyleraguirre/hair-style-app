package com.example.haircutapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.haircutapp.R
import com.example.haircutapp.databinding.FragmentDetailBinding
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.hairstylesdatabase.HairstyleDao
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import kotlinx.android.synthetic.main.fragment_detail.*

abstract class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    private val hairstyle: Hairstyle
        get() {
            hairstyle.aboutStyle
            hairstyle.imagesOfStyle

            return hairstyle
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = HairstyleDatabase.getInstance(application).HairstyleDao

        val viewModelFactory = DetailViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.lifecycleOwner = this

        about_this_style_button.setOnClickListener {
            openWebPage("${hairstyle.aboutStyle}")
        }

        style_images_button.setOnClickListener {
            openWebPage("${hairstyle.imagesOfStyle}")
        }

        add_to_favorites_button.setOnClickListener {
            //need to have style get added to favorites fragment
            //show a toast that the style has been added to fragment
            Toast.LENGTH_LONG
        }


        return binding.root
    }

    fun openWebPage(url: String) {
        val webPage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)

        activity?.startActivity(intent)
    }
}