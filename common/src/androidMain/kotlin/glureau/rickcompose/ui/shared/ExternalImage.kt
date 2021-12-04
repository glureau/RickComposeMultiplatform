package glureau.rickcompose.ui.shared


import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import io.ktor.client.*

@Composable
actual fun ExternalImage(client: HttpClient, modifier: Modifier, url: String?, contentDescription: String?) {
    Image(painter = rememberImagePainter(url), contentDescription = contentDescription, modifier = modifier)
}