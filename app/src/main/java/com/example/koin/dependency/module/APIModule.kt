package com.example.koin.dependency.module

import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * For API (Retrofit)
 * */
object APIModule {

    val apiModule = module {
        /*
        single {
            provideRetrofit()
        }

        single {
            provideOkHttpClient(get<OkHttpClient>())

        factory<CountryAPI> {
            provideCountryApi(get<Retrofit>())
        }
        */
    }
    /*
    private fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideOkHttpClient(okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_DOMAIN)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }

    private fun provideCountryApi(retrofit : Retrofit) : CountryAPI = retrofit.create(CountryAPI::class.java)
    */
}