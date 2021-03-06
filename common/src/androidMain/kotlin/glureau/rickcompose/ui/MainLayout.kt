package glureau.rickcompose.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
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

    val navController = rememberNavController()

    val bottomNavigationItems = listOf(Screens.CharactersScreen, Screens.EpisodesScreen, Screens.LocationsScreen)
    val bottomBar: @Composable () -> Unit = { MortyBottomNavigation(navController, bottomNavigationItems) }

    NavHost(navController, startDestination = Screens.CharactersScreen.route) {
        composable(Screens.CharactersScreen.route) {
            CharactersListView(repository, bottomBar) {
                navController.navigate(Screens.CharacterDetailsScreen.route + "/${it.id}")
            }
        }
        composable(Screens.CharacterDetailsScreen.route + "/{id}") { backStackEntry ->
            CharacterDetailView(
                repository,
                backStackEntry.arguments?.get("id") as String,
                popBack = { navController.popBackStack() })
        }
        composable(Screens.EpisodesScreen.route) {
            EpisodesListView(repository, bottomBar) {
                navController.navigate(Screens.EpisodeDetailsScreen.route + "/${it.id}")
            }
        }
        composable(Screens.EpisodeDetailsScreen.route + "/{id}") { backStackEntry ->
            EpisodeDetailView(
                repository,
                backStackEntry.arguments?.get("id") as String,
                popBack = { navController.popBackStack() })
        }
        composable(Screens.LocationsScreen.route) {
            LocationsListView(repository, bottomBar) {
                navController.navigate(Screens.LocationDetailsScreen.route + "/${it.id}")
            }
        }
        composable(Screens.LocationDetailsScreen.route + "/{id}") { backStackEntry ->
            LocationDetailView(
                repository,
                backStackEntry.arguments?.get("id") as String,
                popBack = { navController.popBackStack() })
        }
    }
}


@Composable
private fun MortyBottomNavigation(
    navController: NavHostController,
    items: List<Screens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon?.let { Icon(screen.icon, contentDescription = screen.label) } },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }

}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
