package glureau.geno.ui.shared


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import io.ktor.client.*
import io.ktor.client.request.*
import org.jetbrains.skia.Image
import androidx.compose.foundation.Image as ComposeImage

suspend fun HttpClient.loadPicture(url: String?): Result<ImageBitmap> {
    if (url == null) return Result.failure(NullPointerException())

    return try {
        val image = get<ByteArray>(url)
        Result.success(Image.makeFromEncoded(image).toComposeImageBitmap())
    } catch (e: Exception) {
        Result.failure(e)
    }
}

@Composable
actual fun ExternalImage(client: HttpClient, modifier: Modifier, url: String?, contentDescription: String?) {
    var isLoading by remember { mutableStateOf(false) }
    var hasFail by remember { mutableStateOf(false) }
    var imageBitmap: ImageBitmap? by remember { mutableStateOf(null) }

    LaunchedEffect(url) {
        isLoading = true
        println("loadPicture $url")
        client.loadPicture(url)
            .onSuccess {
                imageBitmap = it
            }
            .onFailure {
                it.printStackTrace()
                hasFail = true
            }
        isLoading = false
    }

    when {
        isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        hasFail -> {
            //TODO()
        }
        else -> {
            imageBitmap?.let { bitmap ->

                ComposeImage(
                    bitmap = bitmap,
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    alpha = DefaultAlpha,
                    colorFilter = null,
                )

            } //?: TODO()
        }
    }
}