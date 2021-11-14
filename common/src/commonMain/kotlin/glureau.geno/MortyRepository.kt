package glureau.geno

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.network.http.HttpNetworkTransport

class MortyRepository {
    private val apolloClient = ApolloClient.Builder().networkTransport(
        networkTransport = HttpNetworkTransport(
            serverUrl = "https://rickandmortyapi.com/graphql",
        )
    ).build()

    suspend fun getCharacters(page: Int): ApolloResponse<GetCharactersQuery.Data> {
        return apolloClient.query(GetCharactersQuery(page)).execute()
    }

    suspend fun getEpisodes(page: Int): ApolloResponse<GetEpisodesQuery.Data> {
        return apolloClient.query(GetEpisodesQuery(page)).execute()
    }
}
