/*package dev.johnoreilly.mortycomposekmm.ui.locations

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import glureau.rickcompose.data.MortyRepository
import rickcompose.fragment.LocationDetail


@Composable
fun LocationsListView(
    repository: MortyRepository,
    bottomBar: @Composable () -> Unit,
    locationSelected: (location: LocationDetail) -> Unit
) {
    val lazyLocationsList = repository.locations.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Locations") }) },
        bottomBar = bottomBar
    )
    {
        LazyColumn(contentPadding = it) {
            items(lazyLocationsList) { location ->
                LocationsListRowView(location, locationSelected)
            }
        }
    }
}
*/