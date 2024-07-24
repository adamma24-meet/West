package com.example.west.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arcgismaps.mapping.ArcGISMap
import com.arcgismaps.mapping.BasemapStyle
import com.arcgismaps.mapping.Viewpoint
import com.arcgismaps.toolkit.geoviewcompose.MapView

@Composable
fun MapScreen() {
    val map = remember {
        createMap()
    }
    MapView(
        modifier = Modifier.fillMaxSize(),
        arcGISMap = map
    )
}

fun createMap(): ArcGISMap {
    return ArcGISMap(BasemapStyle.ArcGISTopographic).apply {
        initialViewpoint = Viewpoint(
            latitude = 34.0270,
            longitude = -118.8050,
            scale = 72000.0
        )
    }
}
