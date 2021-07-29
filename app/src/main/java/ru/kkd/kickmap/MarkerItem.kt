package ru.kkd.kickmap

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MarkerItem(
    val place : Place
) : ClusterItem {
    override fun getPosition(): LatLng = place.latLng
    override fun getTitle(): String? = place.name
    override fun getSnippet(): String? = ""
}