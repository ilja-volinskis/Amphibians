package com.example.amphibians.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibianData

@Composable
fun AmphibiansList(
    amphibians: List<AmphibianData>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier
    ) {
        items(items = amphibians, key = { it.name }) { amphibian ->
            AmphibianCard(
                amphibian,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: AmphibianData,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.amphibian),
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}