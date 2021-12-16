package com.examples.di

import com.examples.common.Constants
import com.examples.network.repository.PawnRepositoryImpl
import com.examples.network.repository.PawnRepository
import com.examples.network.PawnApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

/*
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
*/

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePawnApi(retrofit: Retrofit): PawnApi =
        retrofit.create(PawnApi::class.java)

    @Provides
    @Singleton
    fun providePawnRepository(pawnApi: PawnApi): PawnRepository {
        //replace test/fake repository if required, as no dependencies created in project
        return PawnRepositoryImpl(pawnApi)
    }
}