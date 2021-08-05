package ru.kkd.kickmap

import com.google.android.gms.maps.model.LatLng

class PlaceList {
    public var list = mutableListOf<Place>()
    init{
        list.add(Place(1, "Москва-сити", LatLng(55.749590, 37.535300)))
        list.add(Place(2, "Кремль", LatLng(55.751465, 37.618830)))
        list.add(Place(3, "ВДНХ", LatLng(55.826453, 37.637875)))
        list.add(Place(4, "ЦПКиО им.Горького", LatLng(55.729942, 37.601391)))
        list.add(Place(5, "МГУ им.Ломоносова", LatLng(55.703050, 37.531014)))

    }
}