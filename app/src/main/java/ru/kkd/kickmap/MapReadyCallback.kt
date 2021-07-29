package ru.kkd.kickmap

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager

class MapReadyCallback(
    val context : Context
) : OnMapReadyCallback {

    private var clusterManager : ClusterManager<MarkerItem>? = null

    override fun onMapReady(gm: GoogleMap) {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            gm.isMyLocationEnabled = true
        addMarkers(gm)
    }

    fun addMarkers(map: GoogleMap) {
        clusterManager = ClusterManager(context, map)
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
        val list = PlaceList().list
        list.forEach()
        {
            clusterManager?.addItem(MarkerItem(it))
        }
    }
}