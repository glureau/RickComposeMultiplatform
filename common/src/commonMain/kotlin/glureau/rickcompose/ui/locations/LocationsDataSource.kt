/*package dev.johnoreilly.mortycomposekmm.ui.locations

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.johnoreilly.mortycomposekmm.fragment.LocationDetail
import dev.johnoreilly.mortycomposekmm.shared.MortyRepository
import glureau.rickcompose.data.MortyRepository
import rickcompose.fragment.LocationDetail

class LocationsDataSource(private val repository: MortyRepository) : PagingSource<Int, LocationDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationDetail> {
        val pageNumber = params.key ?: 0

        val locationsResponse = repository.getLocations(pageNumber)
        val episodes = locationsResponse.results.mapNotNull { it?.locationDetail }

        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = locationsResponse.info.next
        return LoadResult.Page(data = episodes, prevKey = prevKey, nextKey = nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, LocationDetail>): Int? {
        return null
    }
}*/