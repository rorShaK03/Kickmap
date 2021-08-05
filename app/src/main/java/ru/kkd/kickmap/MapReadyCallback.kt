package ru.kkd.kickmap

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.clustering.ClusterManager

class MapReadyCallback(
    val context : Context
) : OnMapReadyCallback {

    private var clusterManager : ClusterManager<MarkerItem>? = null
    var drawed : Boolean = false
    var pos : LatLng? = null

    override fun onMapReady(gm: GoogleMap) {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            gm.isMyLocationEnabled = true
        addMarkers(gm)
        val list = PlaceList().list
        if(!drawed)
        {
            drawed = true
            drawRoute(gm, list)
        }

    }

    fun drawRoute(gm : GoogleMap, lst : List<Place>)
    {
        Routes(context, gm).getPathFromAPI(lst[0], lst[1], lst)
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