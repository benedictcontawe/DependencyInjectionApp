package com.example.koin.module

import org.koin.core.module.Module
import org.koin.dsl.module
/**
 * For API (Retrofit)
 * */
object APIModule {

    val module : Module = module {
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