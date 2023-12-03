package com.example.artapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artapplication.ui.theme.ArtApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    artTimeLayout()
                }
            }
        }
    }
}

data class ArtInformation(val location: Int, val name: String, val art: String)





@Composable
fun artTimeLayout() {
    var indexLocation by remember { mutableStateOf(0) }
    val artList = remember {
        listOf(
            ArtInformation(location = 0, name = "Van Gogh", art = "Vazodaki Güller"),
            ArtInformation(location = 1, name = "Picasso", art = "Sarı Kız"),
            ArtInformation(location = 2, name = "Leonardo da Vinci", art = "Mona Lisa"),
            ArtInformation(location = 3, name = "Rembrandt", art = "Gece Devriyesi"),
            ArtInformation(location = 4, name = "Frida Kahlo", art = "Kahverengi Tonlar"),
        )
    }
    var randomTrigger by remember { mutableStateOf(0) }
    val randomState = rememberUpdatedState(randomTrigger)

    // Call artTimeLayout when randomState changes
    if (randomState.value != randomTrigger) {
        artTimeLayout()
        randomTrigger = randomState.value
    }

    Column {
        Row(
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxHeight(0.5f)
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            val currentImageRes = when (indexLocation) {
                0 -> R.drawable.vazodaki_guller
                1 -> R.drawable.sari_kiz_picasso
                2 -> R.drawable.mona_lisa
                3->R.drawable.gece_devriyesi
                // ... Add more cases as needed
                else -> R.drawable.frida_kahlo}

            Image(
                painter = painterResource(id = currentImageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(500.dp)
                    .aspectRatio(1f)  // Set aspect ratio to make sure images are square
                    .clip(CircleShape) // You can clip images into a circle if needed
            )

        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Column(
            modifier = Modifier
                .fillMaxHeight(0.2f)

                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = artList.get(indexLocation).name,
                textAlign = TextAlign.Center
            )
            Text(
                text = artList.get(indexLocation).art,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(

                onClick = { if (indexLocation==0){
                    indexLocation=artList.size - 1
                } else{
                    indexLocation-=1
                }},

                modifier = Modifier.size(width = 120.dp, height = 40.dp)) {
                Text(text = "BACK")
            }
            Spacer(Modifier.size(20.dp))
            OutlinedButton(
                onClick = { indexLocation = if (indexLocation == artList.size - 1) 0 else indexLocation + 1},
                modifier = Modifier.size(width = 120.dp, height = 40.dp)) {
                Text(text = "NEXT")
            }
        }
        Spacer(Modifier.size(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = {
                    indexLocation = Random.nextInt(artList.size)
                    randomTrigger++



                }
                , modifier = Modifier.size(width = 120.dp, height = 120.dp)) {
                Text(text = "RANDOM")
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtApplicationTheme {
        artTimeLayout()
    }
}