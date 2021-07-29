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
        list.add(Place(6, "Музей-заповедник Царицыно", LatLng(55.615534, 37.688357)))
        list.add(Place(7, "Государственная Третьяковская галлерея", LatLng(55.741385, 37.620528)))
        list.add(Place(8, "Большой театр", LatLng(55.759905, 37.618701)))
        list.add(Place(9, "Музей-заповедник Коломенское", LatLng(55.660662, 37.663196)))
        list.add(Place(10, "Патриаршие пруды", LatLng(55.763852, 37.592132)))
    }
}