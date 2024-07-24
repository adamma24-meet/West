package com.example.west.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.arcgismaps.Color
import com.arcgismaps.geometry.Point
import com.arcgismaps.geometry.SpatialReference
import com.arcgismaps.mapping.ArcGISMap
import com.arcgismaps.mapping.BasemapStyle
import com.arcgismaps.mapping.Viewpoint
import com.arcgismaps.mapping.symbology.SimpleLineSymbol
import com.arcgismaps.mapping.symbology.SimpleLineSymbolStyle
import com.arcgismaps.mapping.symbology.SimpleMarkerSymbol
import com.arcgismaps.mapping.symbology.SimpleMarkerSymbolStyle
import com.arcgismaps.mapping.view.Graphic
import com.arcgismaps.mapping.view.GraphicsOverlay
import com.arcgismaps.toolkit.geoviewcompose.MapView
import com.example.west.utils.LocationUtils
import kotlinx.coroutines.launch

@Composable
fun MapScreen(context: Context) {

    var map by remember { mutableStateOf<ArcGISMap?>(null) }
    val graphicsOverlay = remember { GraphicsOverlay() }

    LaunchedEffect(Unit) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            0
        )

        val locationUtils = LocationUtils(context)
        val currentLocation = locationUtils.getCurrentLocation()
        if (currentLocation != null) {
            val (latitude, longitude) = currentLocation
            map = ArcGISMap(BasemapStyle.ArcGISNavigation).apply {
                initialViewpoint = Viewpoint(
                    latitude = latitude,
                    longitude = longitude,
                    scale = 50000.0
                )
            }

            val point = Point(
                x = longitude,
                y = latitude,
                spatialReference = SpatialReference.wgs84()
            )

            val simpleMarkerSymbol = SimpleMarkerSymbol(
                style = SimpleMarkerSymbolStyle.Circle,
                color = Color.cyan,
                size = 15f
            )
            val blueOutlineSymbol = SimpleLineSymbol(SimpleLineSymbolStyle.Solid, Color.fromRgba(0, 0, 255), 2f)
            simpleMarkerSymbol.outline = blueOutlineSymbol

            val graphic = Graphic(
                geometry = point,
                symbol = simpleMarkerSymbol
            )
            graphicsOverlay.graphics.add(graphic)

        } else {
            map = ArcGISMap(BasemapStyle.ArcGISNavigation).apply {
                initialViewpoint = Viewpoint(
                    latitude = 34.0270,
                    longitude = -118.8050,
                    scale = 72000.0
                )
            }
        }
    }

    map?.let {
        MapView(
            modifier = Modifier.fillMaxSize(),
            arcGISMap = it,
            graphicsOverlays = listOf(graphicsOverlay)
        )
    }
}
