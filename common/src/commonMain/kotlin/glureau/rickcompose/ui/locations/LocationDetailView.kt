package dev.johnoreilly.mortycomposekmm.ui.locations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import glureau.rickcompose.client
import glureau.rickcompose.data.MortyRepository
import glureau.rickcompose.ui.shared.ExternalImage
import rickcompose.fragment.LocationDetail


@Composable
fun LocationDetailView(repository: MortyRepository, locationId: String, popBack: () -> Unit) {
    val (location, setLocation) = remember { mutableStateOf<LocationDetail?>(null) }

    LaunchedEffect(locationId) {
        setLocation(repository.getLocation(locationId))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(location?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        })
    {
        Surface(color = Color.LightGray) {

            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                location?.let {

                    item {

                        Text(
                            "Residents",
                            style = MaterialTheme.typography.h5,
                            color = LocalContentColor.current,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )

                        Surface(color = Color.White) {
                            LocationResidentList(location)
                        }

                    }
                }
            }
        }
    }

}

@Composable
private fun LocationResidentList(location: LocationDetail) {

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        location.residents.filterNotNull().forEach { resident ->
            Row(modifier = Modifier.padding(vertical = 8.dp)) {

                Surface(
                    modifier = Modifier.size(28.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                ) {
                    ExternalImage(
                        client = client,
                        url = resident.image,
                        modifier = Modifier.size(28.dp),
                        contentDescription = resident.name
                    )
                }

                Text(
                    resident.name,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    style = MaterialTheme.typography.h6
                )
            }
            Divider()
        }
    }
}

