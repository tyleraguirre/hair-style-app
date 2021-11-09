package com.example.haircutapp.ui.styles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haircutapp.R
import com.example.haircutapp.SharedViewModel
import com.example.haircutapp.StylesAdapter
import com.example.haircutapp.databinding.FragmentStylesBinding
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase

class StylesFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var binding: FragmentStylesBinding
    private lateinit var viewModel: StylesViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_styles, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = HairstyleDatabase.getInstance(application).HairstyleDao

        val viewModelFactory = StylesViewModelFactory(dataSource, application)

        viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(StylesViewModel::class.java)

        val manager = GridLayoutManager(activity, 3)

        val adapter = StylesAdapter(viewModel, sharedViewModel)


        viewModel.selectedStyle.observe(viewLifecycleOwner, Observer {
            it?.let { hairstyle ->
                // Navigate to detail fragment
                this.findNavController().navigate(
                    StylesFragmentDirections.actionNavigationStylesToDetailFragment(hairstyle))

                viewModel.navigationComplete()
            }
        })
        viewModel.hairstylesList.observe(viewLifecycleOwner, Observer { hairstyleList ->
            val sortedStyles = hairstyleList.sortedBy { it.styleName }
            adapter.submitList(sortedStyles)
        })

        binding.stylesRecyclerview.layoutManager = manager

        binding.stylesRecyclerview.adapter = adapter

        return binding.root

    }
}


