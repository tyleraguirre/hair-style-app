package com.example.haircutapp.ui.styles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haircutapp.R
import com.example.haircutapp.StylesAdapter
import com.example.haircutapp.databinding.FragmentStylesBinding
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase

class StylesFragment : Fragment() {

    private lateinit var binding: FragmentStylesBinding
    private lateinit var viewModel: StylesViewModel

//    private val stylesList = StylesObject.listOfHairStyles

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_styles, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = HairstyleDatabase.getInstance(application).HairstyleDao

        val viewModelFactory = StylesViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StylesViewModel::class.java)

        val manager = GridLayoutManager(activity, 3)

        val adapter = StylesAdapter(viewModel)


        viewModel.selectedStyle.observe(viewLifecycleOwner, Observer {
            it?.let { hairstyle ->
                // Navigate to detail fragment
                this.findNavController().navigate(
                    StylesFragmentDirections.actionNavigationStylesToDetailFragment(hairstyle))
                viewModel.navigationComplete()
            }
        })

        viewModel.hairstylesList.observe(viewLifecycleOwner, Observer { hairstyleList ->
            adapter.submitList(hairstyleList)
        })



        binding.stylesRecyclerview.layoutManager = manager

        binding.stylesRecyclerview.adapter = adapter

//        adapter.submitList(stylesList)

        return binding.root

    }
}


