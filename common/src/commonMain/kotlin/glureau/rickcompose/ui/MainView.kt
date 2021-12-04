package glureau.rickcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import glureau.rickcompose.data.MortyRepository
import rickcompose.GetCharactersQuery

@Composable
fun MainView() {
    val repo = MortyRepository()
    var characters by remember { mutableStateOf<GetCharactersQuery.Characters?>(null) }
    LaunchedEffect(null) {
        characters = repo.getCharacters(0).data?.characters
        println("Retrieved ${characters?.results?.size} characters")
    }

    Column {
        if (characters == null) {
            Text("Hello, data is loading...")
        }

        LazyColumn(Modifier.fillMaxSize()) {
            characters?.results?.filterNotNull()?.forEach { r ->
                item {
                    CharacterView(r.characterDetail) {}
                }
            }
        }
    }
}