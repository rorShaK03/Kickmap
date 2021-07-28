package ru.kkd.kickmap

import com.google.android.gms.maps.model.LatLng

data class Place(val name: String, val latLng : LatLng)

data class PlaceResponse(val name: String, val loc : Geom)

data class Geom(val lat : Double, val lng : Double)

fun PlaceResponse.toPlace() : Place = Place(name, LatLng(loc.lat, loc.lng))