package com.example.myapp.app.di

import com.example.myapp.BuildConfig
import com.example.myapp.app.data.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


/**
Crete by Minh at 20/02/2022
 **/

@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().also { http ->
            http.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(clientHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(clientHttp)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}
