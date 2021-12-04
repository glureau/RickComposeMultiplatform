package glureau.rickcompose
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import glureau.rickcompose.App

actual fun getPlatformName(): String = "Desktop"

@Preview
@Composable
fun AppPreview() {
    App()
}