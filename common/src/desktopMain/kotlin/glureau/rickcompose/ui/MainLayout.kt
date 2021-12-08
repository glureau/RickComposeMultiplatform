package glureau.rickcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import glureau.rickcompose.ui.characters.CharactersListView
import glureau.rickcompose.data.MortyRepository


@Composable
actual fun MainLayout() {
    val scope = rememberCoroutineScope()
    val repository = remember { MortyRepository(scope) }
    CharactersListView(repository, {}) {
        println("GREG - route = CharactersScreen/${it.id}")
        //navController.navigate(Screens.CharacterDetailsScreen.route+ "/${it.id}")
    }
}