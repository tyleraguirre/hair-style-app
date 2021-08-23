package com.example.haircutapp.ui.styles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.haircutapp.databinding.FragmentStylesBinding

class StylesFragment : Fragment() {

    private lateinit var stylesViewModel: StylesViewModel
    private var _binding: FragmentStylesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stylesViewModel =
            ViewModelProvider(this).get(StylesViewModel::class.java)

        _binding = FragmentStylesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textStyles
        stylesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}