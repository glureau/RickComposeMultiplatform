package dev.johnoreilly.mortycomposekmm.ui.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import glureau.rickcompose.data.MortyRepository
import rickcompose.fragment.CharacterDetail

class CharactersDataSource(private val repository: MortyRepository) : PagingSource<Int, CharacterDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDetail> {
        val pageNumber = params.key ?: 0
        println("GREG - params $params")
        val charactersResponse = repository.getCharacters(pageNumber)
        val characters = charactersResponse.dataOrThrow.characters.results.mapNotNull { it?.characterDetail }
        println("GREG - characters ${characters.count()} - ${characters.firstOrNull()}")

        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = charactersResponse.dataOrThrow.characters.info.next
        return LoadResult.Page(data = characters, prevKey = prevKey, nextKey = nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDetail>): Int? {
        return null
    }
}