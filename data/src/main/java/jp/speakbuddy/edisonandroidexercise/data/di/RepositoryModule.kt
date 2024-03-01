package jp.speakbuddy.edisonandroidexercise.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.data.repository.FactRepositoryImpl
import jp.speakbuddy.edisonandroidexercise.data.api.Api
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    companion object {
        @Provides
        @Singleton
        fun provideFactRepository(api: Api): FactRepository {
            return FactRepositoryImpl(api)
        }
    }
}