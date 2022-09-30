package com.example.koin.module

import com.example.koin.network.NasaAPI
import com.example.koin.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    public fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.API_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(provideGsonBuilder()))
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideGsonBuilder() : Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    private fun provideOkHttpClient() : OkHttpClient {
        return  OkHttpClient.Builder()
            .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            /*.addInterceptor(object  : Interceptor {
                override fun intercept(chain: Interceptor.Chain) : Response {
                    val original : Request = chain.request()
                    val request : Request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Authorization", Constants.API_AUTHORIZATION)
                        .method(original.method, original.body)
                        .build()
                    return chain.proceed(request)
                }
            })*/
            .build()
    }

    private fun <S> createService(retrofit : Retrofit, serviceClass : Class<S>?) : S {
        return retrofit.create(serviceClass)
    }

    @Provides
    public fun provideNasaAPI(retrofit : Retrofit) : NasaAPI {
        return createService(retrofit, NasaAPI::class.java)
    }
}