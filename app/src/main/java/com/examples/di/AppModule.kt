package com.examples.di

import android.content.Context
import com.examples.common.Constants
import com.examples.domain.local.IPawnDao
import com.examples.domain.local.PawnDB
import com.examples.network.repository.PawnRepositoryImpl
import com.examples.network.repository.IPawnRepository
import com.examples.network.IPawnApi
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
    fun providePawnDB(context: Context): PawnDB {
        return PawnDB.invoke(context)
    }

    @Provides
    @Singleton
    fun providePawnDao(pawnDB: PawnDB): IPawnDao {
        return pawnDB.getPawnDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePawnApi(retrofit: Retrofit): IPawnApi =
        retrofit.create(IPawnApi::class.java)

    @Provides
    @Singleton
    fun providePawnRepository(pawnApi: IPawnApi, pawnDao: IPawnDao): IPawnRepository {
        //replace test/fake repository if required, as no dependencies created in project
        return PawnRepositoryImpl(pawnApi, pawnDao)
    }
}