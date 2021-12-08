package glureau.rickcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import glureau.rickcompose.data.MortyRepository
import glureau.rickcompose.ui.characters.CharacterDetailView
import glureau.rickcompose.ui.characters.CharactersListView
import glureau.rickcompose.ui.episodes.EpisodeDetailView
import glureau.rickcompose.ui.episodes.EpisodesListView
import glureau.rickcompose.ui.locations.LocationDetailView
import glureau.rickcompose.ui.locations.LocationsListView


@Composable
actual fun MainLayout() {
    val scope = rememberCoroutineScope()
    val repository = remember { MortyRepository(scope) }
    var currentRoute by remember { mutableStateOf(Screens.CharactersScreen.route) }
    val bottomNavigationItems = listOf(Screens.CharactersScreen, Screens.EpisodesScreen, Screens.LocationsScreen)

    val bottomBar: @Composable () -> Unit = { MortyBottomNavigation(currentRoute, { currentRoute = it }, bottomNavigationItems) }

    Column {
        when {
            currentRoute == Screens.CharactersScreen.route -> {
                CharactersListView(repository, bottomBar) {
                    currentRoute = Screens.CharacterDetailsScreen.route + "/" + it.id
                }
            }
            currentRoute.startsWith(Screens.CharacterDetailsScreen.route) -> {
                CharacterDetailView(
                    repository,
                    currentRoute.substringAfter("/"),
                    popBack = { currentRoute = Screens.CharactersScreen.route })
            }
            currentRoute == Screens.EpisodesScreen.route -> {
                EpisodesListView(repository, bottomBar) {
                    currentRoute = Screens.EpisodeDetailsScreen.route + "/" + it.id
                }
            }
            currentRoute.startsWith(Screens.EpisodeDetailsScreen.route) -> {
                EpisodeDetailView(
                    repository,
                    currentRoute.substringAfter("/"),
                    popBack = { currentRoute = Screens.EpisodesScreen.route })
            }
            currentRoute == Screens.LocationsScreen.route -> {
                LocationsListView(repository, bottomBar) {
                    currentRoute = Screens.LocationDetailsScreen.route + "/" + it.id
                }
            }
            currentRoute.startsWith(Screens.LocationDetailsScreen.route) -> {
                LocationDetailView(
                    repository,
                    currentRoute.substringAfter("/"),
                    popBack = { currentRoute = Screens.LocationsScreen.route })
            }
        }
    }
}


@Composable
private fun MortyBottomNavigation(
    currentRoute: String,
    navigate: (route: String) -> Unit,
    items: List<Screens>
) {
    BottomNavigation {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon?.let { Icon(screen.icon, contentDescription = screen.label) } },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navigate(screen.route)
                    }
                }
            )
        }
    }

}