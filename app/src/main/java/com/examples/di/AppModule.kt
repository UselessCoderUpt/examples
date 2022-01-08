package com.examples.di

import android.content.Context
import com.examples.BuildConfig
import com.examples.common.Constants
import com.examples.domain.local.ICustomerDao
import com.examples.domain.local.IPawnDao
import com.examples.domain.local.PawnDB
import com.examples.network.repository.PawnRepositoryImpl
import com.examples.network.repository.IPawnRepository
import com.examples.network.IPawnApi
import com.examples.network.repository.CustomerRepositoryImpl
import com.examples.network.repository.ICustomerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providePawnDB(@ApplicationContext context: Context): PawnDB {
        return PawnDB(context)
    }

    @Provides
    @Singleton
    fun providePawnDao(pawnDB: PawnDB) : IPawnDao {
        return pawnDB.getPawnDao()
    }

    @Provides
    @Singleton
    fun provideCustomerDao(pawnDB: PawnDB) : ICustomerDao {
        return pawnDB.getCustomerDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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

    @Provides
    @Singleton
    fun provideCustomerRepository(pawnApi: IPawnApi, customerDao: ICustomerDao): ICustomerRepository {
        //replace test/fake repository if required, as no dependencies created in project
        return CustomerRepositoryImpl(pawnApi, customerDao)
    }
}