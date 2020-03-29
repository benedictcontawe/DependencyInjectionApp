package com.example.koin.dependency.module

import com.example.koin.BuildConfig
import com.example.koin.model.web.CountryAPI
import com.example.koin.model.web.NetworkClient
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * For API (Retrofit)
 * */
object APIModule {

    val apiModule = module {

        single {
            provideRetrofit()
        }
        /*
        single {
            provideOkHttpClient(get<OkHttpClient>())
        }
        */
        factory<CountryAPI> {
            provideCountryApi(get<Retrofit>())
        }

    }

    private fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    /*
    private fun provideOkHttpClient(okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_DOMAIN)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }
    */
    private fun provideCountryApi(retrofit : Retrofit) : CountryAPI = retrofit.create(CountryAPI::class.java)

}