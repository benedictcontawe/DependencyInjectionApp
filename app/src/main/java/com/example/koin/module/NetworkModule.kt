package com.example.koin.module

import org.koin.core.module.Module
import org.koin.dsl.module
/**
 * For API (Retrofit)
 * */
object NetworkModule {

    val module : Module = module {
        /*
        single {
            provideRetrofit(Constants.API_DOMAIN, get<Gson>(), get<OkHttpClient>())
        }

        single {
            provideOkHttpClient(Constants.TIMEOUT)
        }

        single {
            provideGsonBuilder()
        }

        factory<NasaAPI> {
            provideNasaAPI(get<Retrofit>())
        }
        */
    }
    /*
    private fun provideRetrofit(url : String, gson : Gson, okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    private fun provideGsonBuilder() : Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    private fun provideOkHttpClient(timeout : Long) : OkHttpClient {
        return  OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            addInterceptor(object  : Interceptor {
                override fun intercept(chain: Interceptor.Chain) : Response {
                    val original : Request = chain.request()
                    val request : Request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Authorization", Constants.API_AUTHORIZATION)
                        .method(original.method, original.body)
                        .build()
                    return chain.proceed(request)
                }
            })
            .build()
    }

    private fun <S> createService(retrofit : Retrofit, serviceClass : Class<S>?) : S {
        return retrofit.create(serviceClass)
    }

    private fun provideNasaAPI(retrofit : Retrofit) : NasaAPI {
        return createService(retrofit, NasaAPI::class.java)
    }
    */
}