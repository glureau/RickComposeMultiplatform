/*package dev.johnoreilly.mortycomposekmm.ui.episodes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.johnoreilly.mortycomposekmm.fragment.EpisodeDetail
import dev.johnoreilly.mortycomposekmm.ui.MainViewModel
import glureau.rickcompose.data.MortyRepository
import rickcompose.fragment.EpisodeDetail

@Composable
fun EpisodesListView(repository: MortyRepository, bottomBar: @Composable () -> Unit, episodeSelected: (episode: EpisodeDetail) -> Unit) {
    val lazyEpisodeList = repository.episodes.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text( "Episodes") }) },
        bottomBar = bottomBar)
    {
        LazyColumn(contentPadding = it) {
            items(lazyEpisodeList) { episode ->
                episode?.let {
                    EpisodesListRowView(episode, episodeSelected)
                }
            }
        }
    }
}*/

