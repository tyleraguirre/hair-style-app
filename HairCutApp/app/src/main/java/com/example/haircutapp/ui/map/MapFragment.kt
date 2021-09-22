package com.example.haircutapp.ui.map

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.haircutapp.R
//import com.example.haircutapp.databinding.FragmentMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

private lateinit var map: GoogleMap
private lateinit var fusedLocationClient: FusedLocationProviderClient

class MapFragment : Fragment(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
//    private val callback = OnMapReadyCallback { googleMap ->

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

//        googleMap.setPadding(0,0,0,0)


        val location = LatLng(37.09887924093022, -113.59157344165415)
        map.addMarker(MarkerOptions().position(location).title("I am here"))
        map.moveCamera(CameraUpdateFactory.newLatLng(location))

        map.getUiSettings().setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)
    }




//            val zoomAmount = 12.0F

//            val location = LatLng(37.09874769007267, -113.59165704074472)
//            googleMap.addMarker(location.let { MarkerOptions().position(it).title(location.toString()) })
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoomAmount))


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    override fun onMarkerClick(p0: Marker?) = false
}

