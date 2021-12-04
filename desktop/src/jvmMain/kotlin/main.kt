import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import glureau.rickcompose.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication, resizable = true, title = "Rick & Morty") {
        App()
    }
}