package com.filip.tecajnalistahnb.di

import android.content.Context
import com.filip.tecajnalistahnb.api.ApiService
import com.filip.tecajnalistahnb.common.BASE_BACKEND_URL
import com.filip.tecajnalistahnb.coroutines.CoroutineContextProviderImpl
import com.filip.tecajnalistahnb.interactor.BackendInteractor
import com.filip.tecajnalistahnb.interactor.BackendInteractorInterface
import com.filip.tecajnalistahnb.ui.CurrencyViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val PREFERENCES_NAME = "tecajnaListaHnbPrefs"
private const val LOGGING_INTERCEPTOR = "logging"
private const val BACKEND_RETROFIT_TECAJNA_LISTA = "tecajnaListaHnbBackend"

val appModule = module {
    single { androidContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE) }
//    single<PrefsHelperInterfae> { PrefsHelper(get()) }
}

val networkingModule = module {

    single { GsonConverterFactory.create() as Converter.Factory }

    single(named(LOGGING_INTERCEPTOR)) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    //okHttpClient
    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(get<HttpLoggingInterceptor>(named(LOGGING_INTERCEPTOR)))
            }
            readTimeout(1L, TimeUnit.MINUTES)
            connectTimeout(1L, TimeUnit.MINUTES)
        }.build()
    }
    single(named(BACKEND_RETROFIT_TECAJNA_LISTA)) {
        Retrofit.Builder()
            .baseUrl(BASE_BACKEND_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
    }
    single { get<Retrofit>(named(BACKEND_RETROFIT_TECAJNA_LISTA)).create(ApiService::class.java) }
}

val interactionModule = module {
    single<BackendInteractorInterface> { BackendInteractor(get()) }
}

val coroutineModule = module {
    single { CoroutineContextProviderImpl() }
}

val viewModule = module {
    viewModel { CurrencyViewModel(get(),get()) }
}

val modules = listOf(
    appModule,
    coroutineModule,
    networkingModule,
    interactionModule,
    viewModule)