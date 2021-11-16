package com.example.haircutapp.ui.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.haircutapp.R
import android.net.Uri
import androidx.databinding.DataBindingUtil
import com.example.haircutapp.databinding.FragmentMapBinding
import com.example.haircutapp.util.fadeInText
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.MapFragment
import kotlinx.android.synthetic.main.fragment_map.*



class MapFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lastLocation: Location? = null
    private lateinit var uri: Uri
    private lateinit var locationIntent: Intent
    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_map,container,false)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        binding.googleMapNavButton.fadeInText()
        binding.mapButtonInfoText.fadeInText()

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    lastLocation = location
                }
            }
        binding.googleMapNavButton.setOnClickListener {
            launchIntent()
        }
        return binding.root
    }

    /*This launches a GoogleMap intent that uses the users current location so they can search
    for barbers and salons in their area
     */
    fun launchIntent() {

        uri = Uri.parse("geo:${lastLocation?.latitude}, ${lastLocation?.longitude}")
        locationIntent = Intent(Intent.ACTION_VIEW, uri)
        locationIntent.setPackage("com.google.android.apps.maps")
        activity?.startActivity(locationIntent)
    }
}
