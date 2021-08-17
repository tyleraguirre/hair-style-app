package com.example.hairstyleapp.hintpage

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import com.example.hairstyleapp.R
import com.example.hairstyleapp.databinding.HintFragmentBinding

class HintFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: HintFragmentBinding = inflate(
            inflater, R.layout.hint_fragment, container, false)

        val application = requireNotNull(this.activity).application

        binding.lifecycleOwner = this

        return binding.root

    }
}