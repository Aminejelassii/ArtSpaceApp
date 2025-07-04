package com.example.artspaceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Artwork(
    val imageResId: Int,
    val title: String,
    val artist: String,
    val year: String,
    val contentDescription: String
)

@Composable
fun ArtSpaceApp() {
    // List of artworks
    val artworks = listOf(
        Artwork(
            imageResId = R.drawable.artwork_1_foreground,
            title = "Starry Night",
            artist = "Vincent van Gogh",
            year = "1889",
            contentDescription = "Painting of a starry night sky over a quiet town"
        ),
        Artwork(
            imageResId = R.drawable.artwork_2_foreground,
            title = "The Scream",
            artist = "Edvard Munch",
            year = "1893",
            contentDescription = "Expressionist painting of a figure screaming on a bridge"
        ),
        Artwork(
            imageResId = R.drawable.artwork_3_background,
            title = "Mona Lisa",
            artist = "Leonardo da Vinci",
            year = "1503",
            contentDescription = "Portrait of a woman with a subtle smile"
        )
    )

    // State to track the current artwork index
    var currentIndex by remember { mutableStateOf(0) }

    // Calculate indices for navigation
    val totalArtworks = artworks.size
    val nextIndex = if (currentIndex < totalArtworks - 1) currentIndex + 1 else 0
    val prevIndex = if (currentIndex > 0) currentIndex - 1 else totalArtworks - 1

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Artwork Image Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
                    .border(2.dp, Color.Gray),
                shadowElevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = artworks[currentIndex].imageResId),
                    contentDescription = artworks[currentIndex].contentDescription,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Artwork Details Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = artworks[currentIndex].title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${artworks[currentIndex].artist} (${artworks[currentIndex].year})",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Navigation Buttons Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { currentIndex = prevIndex },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Previous")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { currentIndex = nextIndex },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}