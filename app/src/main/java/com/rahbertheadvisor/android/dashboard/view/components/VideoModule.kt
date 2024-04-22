package com.rahbertheadvisor.android.dashboard.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rahbertheadvisor.android.dashboard.model.VideoModule

@Composable
fun VideoModule(videoModule: VideoModule) {
    Row {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(60.dp).padding(5.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(videoModule.thumbUrl)
                .crossfade(true)
                .build(),
            contentDescription = videoModule.name
        )
        Text(text = videoModule.name,
            style = TextStyle(fontStyle = FontStyle.Italic),
            modifier = Modifier.align(Alignment.CenterVertically)
                .padding(start = 5.dp)
                .fillMaxWidth())
    }
}