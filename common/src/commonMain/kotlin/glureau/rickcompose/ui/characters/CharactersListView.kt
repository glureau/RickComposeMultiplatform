package dev.johnoreilly.mortycomposekmm.ui.characters

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import collectAsLazyPagingItems
import glureau.rickcompose.data.MortyRepository
import items
import rickcompose.fragment.CharacterDetail


@Composable
fun CharactersListView(
    repository: MortyRepository,
    bottomBar: @Composable () -> Unit,
    characterSelected: (character: CharacterDetail) -> Unit
) {
    val lazyCharacterList = repository.characterPagingData.collectAsLazyPagingItems<CharacterDetail>()

    repository.characterPagingData

    Scaffold(
        topBar = { TopAppBar(title = { Text("Characters") }) },
        bottomBar = bottomBar
    )
    {
        LazyColumn(contentPadding = it) {
            items(lazyCharacterList) { character ->
                CharactersListRowView(character!!, characterSelected)
            }
        }
    }
}