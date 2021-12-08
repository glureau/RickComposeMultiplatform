package glureau.rickcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import glureau.rickcompose.data.MortyRepository
import rickcompose.GetCharactersQuery

@Composable
fun MainView() {
    val scope = rememberCoroutineScope()
    val repo = MortyRepository(scope)
    var characters by remember { mutableStateOf<GetCharactersQuery.Characters?>(null) }
    LaunchedEffect(null) {
        characters = repo.getCharacters(0).data?.characters
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


sealed class Screens(val route: String, val label: String, val icon: ImageVector? = null) {
    object CharactersScreen : Screens("Characters", "Characters", Icons.Default.Person)
    object EpisodesScreen : Screens("Episodes", "Episodes",  Icons.Default.List)
    object LocationsScreen : Screens("Locations", "Locations",  Icons.Default.LocationOn)
    object CharacterDetailsScreen : Screens("CharacterDetails", "CharacterDetails")
    object EpisodeDetailsScreen : Screens("EpisodeDetails", "EpisodeDetails")
    object LocationDetailsScreen : Screens("LocatonDetails", "LocatonDetails")
}

@Composable
expect fun MainLayout()