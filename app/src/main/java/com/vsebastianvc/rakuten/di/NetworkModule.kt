package com.vsebastianvc.rakuten.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vsebastianvc.rakuten.data.PhotoPageServiceApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sebastian on 11/30/2020.
 */

private const val BASE_URL = "http://yuriy.me/rakuten-rewards/"

val networkModule = module {
    single { provideGson() }
    single { provideRetrofit(get()) }
    single { providePhotoPageServiceApi(get()) }
}

fun providePhotoPageServiceApi(retrofit: Retrofit): PhotoPageServiceApi = retrofit
        .create(PhotoPageServiceApi::class.java)

fun provideRetrofit(converter: Gson): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(converter))
        .build()

fun provideGson(): Gson = GsonBuilder().create()
