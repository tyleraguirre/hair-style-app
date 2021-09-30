package com.example.haircutapp.ui.map

//import com.example.haircutapp.databinding.FragmentMapBinding
//import com.google.android.gms.location.places.ui.PlacePicker

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.haircutapp.R
import com.example.haircutapp.databinding.FragmentMapBinding
import com.example.haircutapp.databinding.FragmentStylesBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import java.io.IOException


class MapFragment : Fragment(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private lateinit var binding: FragmentMapBinding
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)

        setUpMap()
    }


    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
            }
        }
    }
    private fun placeMarkerOnMap(location: LatLng) {

        val markerOptions = MarkerOptions().position(location)

        val titleString = getAddress(location)
        markerOptions.title(titleString)

        map.addMarker(markerOptions)

    }

    private fun getAddress(latLng: LatLng): String {

        val geocoder = Geocoder(requireContext())
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    address = addresses[0]
                    addressText += address.getAddressLine(0)
        }
        catch (e: IOException) {
            Log.e("MapsFragment", e.localizedMessage)
        }
        return addressText
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        val autocompleteFragment = PlaceAutocompleteFragment().fragmentManager.findFragmentById(R.id.map_fragment)

       autocompleteFragment!!.onPlaceSelected(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                map.clear()
                map.addMarker(MarkerOptions().position(place.latLng).title(place.name.toString()))
                map.moveCamera(CameraUpdateFactory.newLatLng(place.latLng))
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(place.latLng, 12.0f))
            }

            override fun onError(status: Status?) {}
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }


//    private fun searchQuery() {
//        val searchView = search_view
//        var location = searchView.query.toString()
//        var searchAddressList = listOf<Address?>()
//
//        if (location != null || location.equals("")) {
//            val geocoder: Geocoder
//
//            try {
//                searchAddressList = geocoder.getFromLocationName(location,1)
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            searchAddressList.get(0)
//            searchAddressList.
//        }
//    }





    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val PLACE_PICKER_REQUEST = 3
    }

    override fun onMarkerClick(p0: Marker?) = false
}

