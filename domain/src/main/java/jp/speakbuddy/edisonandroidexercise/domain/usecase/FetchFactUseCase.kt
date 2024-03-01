package jp.speakbuddy.edisonandroidexercise.domain.usecase

import androidx.datastore.preferences.core.edit
import jp.speakbuddy.edisonandroidexercise.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.data.store.FactDataStore
import timber.log.Timber

class FetchFactUseCase(
    private val factRepository: FactRepository,
    private val factDataStore: FactDataStore,
) {
    suspend operator fun invoke() {
        runCatching {
            factRepository.fetch()
        }.onSuccess { response ->
            Timber.i("Fetch data successful $response")
            factDataStore.data.edit {
                it[FactDataStore.KEY_FACT_CONTENT] = response.fact
                it[FactDataStore.KEY_FACT_LENGTH] = response.length
            }
        }.onFailure {
            Timber.e("Fetch data failure $it")
            throw it
        }
    }
}