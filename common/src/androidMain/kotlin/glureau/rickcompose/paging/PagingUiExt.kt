package glureau.rickcompose.paging

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import androidx.paging.compose.LazyPagingItems as androidxLazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems as androidxCollectAsLazyPagingItems
import androidx.paging.compose.items as androidxItems

@Composable
actual fun <T : Any> Flow<PagingData<T>>.collectAsLazyPagingItems(): LazyPagingItems<T> =
    androidxCollectAsLazyPagingItems()

actual typealias LazyPagingItems<T> = androidxLazyPagingItems<T>

actual fun <T : Any> LazyListScope.lazyItems(
    items: LazyPagingItems<T>,
    //key: ((item: T) -> Any)?,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) = androidxItems(items, null, itemContent)