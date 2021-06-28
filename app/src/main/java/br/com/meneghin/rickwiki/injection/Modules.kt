package br.com.meneghin.rickwiki.injection


import br.com.meneghin.rickwiki.data.dto.character.Character
import br.com.meneghin.rickwiki.data.network.API_URL
import br.com.meneghin.rickwiki.data.network.RickService
import br.com.meneghin.rickwiki.data.repository.CharacterRepositoryImpl
import br.com.meneghin.rickwiki.data.repository.EpisodeRepositoryImpl
import br.com.meneghin.rickwiki.data.repository.LocationRepositoryImpl
import br.com.meneghin.rickwiki.domain.CoroutineContextProvider
import br.com.meneghin.rickwiki.domain.interfaces.CharacterRepository
import br.com.meneghin.rickwiki.domain.interfaces.EpisodeRepository
import br.com.meneghin.rickwiki.domain.interfaces.LocationRepository
import br.com.meneghin.rickwiki.domain.usecase.GetCharactersByPage
import br.com.meneghin.rickwiki.domain.usecase.GetEpisodesByPage
import br.com.meneghin.rickwiki.domain.usecase.GetLocationsByPage
import br.com.meneghin.rickwiki.ui.CharacterPagingSource
import br.com.meneghin.rickwiki.ui.viewmodel.CharacterListViewModel
import br.com.meneghin.rickwiki.ui.viewmodel.CharacterViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit


/***** DATA MODULE *****/

val networkModule = module {

    factory { provideOkHttpClient() }

    single { provideRetrofit(get()) }

}

val repositoryModule = module {

    factory { CharacterRepositoryImpl(get()) } bind CharacterRepository::class

    factory { EpisodeRepositoryImpl(get()) } bind EpisodeRepository::class

    factory { LocationRepositoryImpl(get()) } bind LocationRepository::class
}

val contentType = "application/json".toMediaType()

fun provideRetrofit(okHttpClient: OkHttpClient): RickService {
    return Retrofit.Builder()
        .baseUrl(API_URL)
        .client(okHttpClient)
        .addConverterFactory(Json{ ignoreUnknownKeys = true}
            .asConverterFactory(contentType))
        .build()
        .create(RickService::class.java)
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

val dataModules = listOf(networkModule, repositoryModule)

/***** DOMAIN MODULE *****/
val useCaseModule = module {

    single { CoroutineContextProvider(Dispatchers.Main, Dispatchers.IO) }

    factory {
        GetCharactersByPage(
            coroutineContext = get(),
            repository = get()
        )
    }
    factory {
        GetEpisodesByPage(
            coroutineContext = get(),
            repository = get()
        )
    }
    factory {
        GetLocationsByPage(
            coroutineContext = get(),
            repository = get()
        )
    }
}

val domainModules = listOf(useCaseModule)

/***** PRESENTATION MODULE *****/

val presentationModule = module {

    factory { CharacterPagingSource(get())  }

    viewModel {
        CharacterListViewModel(
            contextProvider = get(),
            getCharacterByPage = get(),
            source = get()
        )
    }
    viewModel { (c: Character) -> CharacterViewModel(c) }

}

val presentationModules = listOf(presentationModule)
