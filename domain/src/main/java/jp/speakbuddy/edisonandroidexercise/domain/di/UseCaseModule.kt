package jp.speakbuddy.edisonandroidexercise.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.data.store.FactDataStore
import jp.speakbuddy.edisonandroidexercise.domain.usecase.FetchFactUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    companion object {

        @Provides
        fun provideFetchFactUseCase(
            factRepository: FactRepository,
            factDataStore: FactDataStore,
        ): FetchFactUseCase {
            return FetchFactUseCase(factRepository, factDataStore)
        }
    }
}