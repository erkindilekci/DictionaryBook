package com.erkindilekci.dictionarybook.feature.di

import android.app.Application
import androidx.room.Room
import com.erkindilekci.dictionarybook.feature.data.local.Converters
import com.erkindilekci.dictionarybook.feature.data.local.WordInfoDao
import com.erkindilekci.dictionarybook.feature.data.local.WordInfoDatabase
import com.erkindilekci.dictionarybook.feature.data.remote.DictionaryApi
import com.erkindilekci.dictionarybook.feature.data.repository.WordInfoRepositoryImpl
import com.erkindilekci.dictionarybook.feature.data.util.GsonParser
import com.erkindilekci.dictionarybook.feature.domain.repository.WordInfoRepository
import com.erkindilekci.dictionarybook.feature.domain.usecase.GetWordInfoUseCase
import com.google.gson.Gson
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

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfoUseCase =
        GetWordInfoUseCase(repository)

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        dao: WordInfoDao,
        api: DictionaryApi
    ): WordInfoRepository = WordInfoRepositoryImpl(api, dao)

    @Provides
    @Singleton
    fun provideWordInfoDatabase(
        application: Application
    ): WordInfoDatabase =
        Room.databaseBuilder(application, WordInfoDatabase::class.java, "word_database")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()

    @Provides
    @Singleton
    fun provideWordInfoDao(db: WordInfoDatabase): WordInfoDao = db.dao

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi = Retrofit.Builder()
        .baseUrl(DictionaryApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DictionaryApi::class.java)
}