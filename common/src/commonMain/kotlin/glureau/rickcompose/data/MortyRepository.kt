package glureau.rickcompose.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.network.http.HttpNetworkTransport
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import kotlinx.coroutines.CoroutineScope
import rickcompose.*
import rickcompose.fragment.CharacterDetail
import rickcompose.fragment.EpisodeDetail
import rickcompose.fragment.LocationDetail

class MortyRepository(clientScope: CoroutineScope) {

    val characterPager = Pager(
        clientScope = clientScope,
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false // Ignored on iOS
        ),
        initialKey = 1,
        getItems = { currentKey, size ->
            println("GREG - getItems $currentKey $size")
            val charactersResponse = getCharacters(currentKey)
            println("GREG - getItems ${charactersResponse.data?.characters?.info?.count} ${charactersResponse.errors}")
            val items = charactersResponse.dataOrThrow.characters.results.mapNotNull { it?.characterDetail }
            PagingResult(
                items = items,
                currentKey = currentKey,
                prevKey = { null },
                nextKey = { charactersResponse.dataOrThrow.characters.info.next }
            )
        }
    )

    // TODO: get() ??
    val characterPagingData = characterPager.pagingData.cachedIn(clientScope)


    private val apolloClient = ApolloClient.Builder().networkTransport(
        networkTransport = HttpNetworkTransport(
            serverUrl = "https://rickandmortyapi.com/graphql",
        )
    ).build()

    suspend fun getCharacters(page: Int): ApolloResponse<GetCharactersQuery.Data> {
        return apolloClient.query(GetCharactersQuery(page)).execute()
    }

    suspend fun getCharacter(characterId: String): CharacterDetail {
        val response = apolloClient.query(GetCharacterQuery(characterId)).execute()
        return response.dataOrThrow.character.characterDetail
    }

    suspend fun getEpisodes(page: Int): GetEpisodesQuery.Episodes {
        val response = apolloClient.query(GetEpisodesQuery(page)).execute()
        return response.dataOrThrow.episodes
    }

    suspend fun getEpisode(episodeId: String): EpisodeDetail {
        val response = apolloClient.query(GetEpisodeQuery(episodeId)).execute()
        return response.dataOrThrow.episode.episodeDetail
    }

    suspend fun getLocations(page: Int): GetLocationsQuery.Locations {
        val response = apolloClient.query(GetLocationsQuery(page)).execute()
        return response.dataOrThrow.locations
    }

    suspend fun getLocation(locationId: String): LocationDetail {
        val response = apolloClient.query(GetLocationQuery(locationId)).execute()
        return response.dataOrThrow.location.locationDetail
    }
}
