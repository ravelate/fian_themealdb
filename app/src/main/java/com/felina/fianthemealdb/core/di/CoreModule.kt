package com.felina.fianthemealdb.core.di

import androidx.room.Room
import com.felina.fianthemealdb.core.data.MealRepository
import com.felina.fianthemealdb.core.data.source.local.LocalDataSource
import com.felina.fianthemealdb.core.data.source.local.room.MealDatabase
import com.felina.fianthemealdb.core.data.source.remote.RemoteDataSource
import com.felina.fianthemealdb.core.data.source.remote.network.ApiService
import com.felina.fianthemealdb.core.domain.repository.IMealRepository
import com.felina.moviefianapp.core.utils.AppExecutors
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient

val databaseModule = module {
    factory { get<MealDatabase>().mealDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MealDatabase::class.java, "Meals.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMealRepository> {
        MealRepository(
            get(),
            get(),
            get()
        )
    }
    single {

        MealRepository(
            get(),
            get(),
            get()
        )
    }
}