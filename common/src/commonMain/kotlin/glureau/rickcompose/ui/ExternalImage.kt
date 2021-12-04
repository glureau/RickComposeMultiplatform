package glureau.rickcompose.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.ktor.client.*

@Composable
expect fun ExternalImage(client: HttpClient, modifier: Modifier = Modifier, url: String?, contentDescription: String?)