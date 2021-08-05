package ru.kkd.kickmap

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection

class Routes(val context : Context, val gm : GoogleMap) {
    val POLYLINE_WIDTH : Float = 10f
    var string_path : String? = null
    fun drawAllPolylines()
    {
        if(string_path != null)
            gm.addPolyline(PolylineOptions().color(context.resources.getColor(R.color.polyline)).width(POLYLINE_WIDTH).clickable(false).addAll(readFromString(string_path!!)))
    }

    fun readFromString(str: String) : MutableList<LatLng>
    {
        var latLngList = mutableListOf<LatLng>()
        latLngList.addAll(PolyUtil.decode(str.trim()))
        return latLngList
    }

    inner class rnbl(val start : Place, val end : Place, val waypoints: List<Place>) : Runnable
    {
       public override fun run()
       {
           getHttps(start, end, waypoints)
       }
    }

    fun getPathFromAPI(start : Place, end : Place, waypoints: List<Place>)
    {
        var thread = Thread(rnbl(start, end, waypoints))
        thread.start()
        thread.join()
        drawAllPolylines()
    }

    fun getHttps(start : Place, end : Place, waypoints : List<Place>)
    {
        var request = "https://maps.googleapis.com/maps/api/directions/json?origin=${start.latLng.latitude},${start.latLng.longitude}&" +
                "destination=${end.latLng.latitude},${end.latLng.longitude}&mode=walking&key=${BuildConfig.MAPS_API_KEY}"
        if(waypoints.size > 0) {
            request += "&waypoints="
            for (i in 0 until waypoints.size) {
                request += "${waypoints[i].latLng.latitude},${waypoints[i].latLng.longitude}"
                if(i < waypoints.size - 1)
                    request += "|"
            }
        }
        var url = URL(request)
        var conn = url.openConnection() as HttpsURLConnection
        conn.connectTimeout = 3000
        conn.setRequestMethod("GET")
        var res = ""
        if(conn.responseCode == HttpsURLConnection.HTTP_OK)
        {
            var line : String? = ""
            var br = BufferedReader(InputStreamReader(conn.inputStream))
            line = br.readLine()
            while(line != null)
            {
                res = res + line + "\r\n"
                line = br.readLine()
            }
            var routes = JSONObject(res).getJSONArray("routes")
            var polyline = routes.getJSONObject(0).getJSONObject("overview_polyline")
            var str_polyline = polyline.getString("points")
            string_path = str_polyline
        }
    }
}