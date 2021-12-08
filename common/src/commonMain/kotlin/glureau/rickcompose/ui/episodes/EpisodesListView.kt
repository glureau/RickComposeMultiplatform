package glureau.rickcompose.ui.episodes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import glureau.rickcompose.data.MortyRepository
import glureau.rickcompose.paging.collectAsLazyPagingItems
import glureau.rickcompose.paging.lazyItems
import rickcompose.fragment.EpisodeDetail

@Composable
fun EpisodesListView(repository: MortyRepository, bottomBar: @Composable () -> Unit, episodeSelected: (episode: EpisodeDetail) -> Unit) {
    val lazyEpisodeList = repository.episodes.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text( "Episodes") }) },
        bottomBar = bottomBar)
    {
        LazyColumn(contentPadding = it) {
            lazyItems(lazyEpisodeList) { episode ->
                episode?.let {
                    EpisodesListRowView(episode, episodeSelected)
                }
            }
        }
    }
}
