package glureau.rickcompose

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import glureau.rickcompose.ui.MainLayout
import glureau.rickcompose.ui.MainView
import io.ktor.client.*

val client = HttpClient()

@Composable
fun App() {
    MaterialTheme {
        //MainView()
        MainLayout()
    }
}

expect fun getPlatformName(): String