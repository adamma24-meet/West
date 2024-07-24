package com.example.west.screens

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.arcgismaps.mapping.ArcGISMap
import com.arcgismaps.mapping.BasemapStyle
import com.arcgismaps.mapping.Viewpoint
import com.arcgismaps.toolkit.geoviewcompose.MapView
import com.example.west.utils.LocationUtils
import kotlinx.coroutines.launch
import android.Manifest
import android.app.Activity
import androidx.core.location.component1
import androidx.core.location.component2

@Composable
fun MapScreen(context: Context) {
    var map by remember { mutableStateOf<ArcGISMap?>(null) }

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
            arcGISMap = it
        )
    }
}
