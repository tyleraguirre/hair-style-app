package com.example.haircutapp.ui.styles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haircutapp.R
import com.example.haircutapp.databinding.FragmentStylesBinding
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase

class StylesFragment : Fragment() {

//    private lateinit var stylesViewModel: StylesViewModel
//    private var _binding: FragmentStylesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentStylesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_styles,
            container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = HairstyleDatabase.getInstance(application).HairstyleDao
        val viewModelFactory = StylesViewModelFactory(dataSource, application)
        val _stylesViewModel = ViewModelProvider(this, viewModelFactory)
            .get(StylesViewModel::class.java)

        val styles = _stylesViewModel.database.getAllHairstyles()
        val recyclerView = binding.stylesList

        binding.stylesViewModel = _stylesViewModel
        binding.lifecycleOwner = this

        val manager = GridLayoutManager(activity, 3)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(false)

//        styles.observe(viewLifecycleOwner, Observer { listOfStyles ->
//            recyclerView.adapter = StylesAdapter(requireContext(), listOfStyles)
//        })

            return binding.root
        }

        override fun onDestroyView() {
            super.onDestroyView()
        }
    }
