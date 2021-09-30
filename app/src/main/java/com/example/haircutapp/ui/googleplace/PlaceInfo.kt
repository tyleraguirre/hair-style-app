package com.example.haircutapp.ui.googleplace

import android.net.Uri
import com.google.android.gms.maps.model.LatLng


/**
 * Created by User on 10/2/2017.
 */
class PlaceInfo {
    var name: String? = null
    var address: String? = null
    var phoneNumber: String? = null
    var id: String? = null
    var websiteUri: Uri? = null
    var latlng: LatLng? = null
    var rating = 0f
    var attributions: String? = null

    constructor(
        name: String?, address: String?, phoneNumber: String?, id: String?, websiteUri: Uri?,
        latlng: LatLng?, rating: Float, attributions: String?
    ) {
        this.name = name
        this.address = address
        this.phoneNumber = phoneNumber
        this.id = id
        this.websiteUri = websiteUri
        this.latlng = latlng
        this.rating = rating
        this.attributions = attributions
    }

    constructor() {}

    override fun toString(): String {
        return "PlaceInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id='" + id + '\'' +
                ", websiteUri=" + websiteUri +
                ", latlng=" + latlng +
                ", rating=" + rating +
                ", attributions='" + attributions + '\'' +
                '}'
    }
}