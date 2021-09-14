package com.example.haircutapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.haircutapp.R
import com.example.haircutapp.databinding.FragmentDetailBinding
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = HairstyleDatabase.getInstance(application).HairstyleDao

        val viewModelFactory = DetailViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.lifecycleOwner = this


        return binding.root
    }

}