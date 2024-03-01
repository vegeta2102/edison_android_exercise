package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.data.store.FactDataStore
import jp.speakbuddy.edisonandroidexercise.domain.usecase.FetchFactUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val fetchFactUseCase: FetchFactUseCase,
    private val factDataStore: FactDataStore
) : ViewModel() {

    val content: Flow<String> = factDataStore.data.data.mapNotNull {
        it[FactDataStore.KEY_FACT_CONTENT]
    }
    val length: Flow<Int> = factDataStore.data.data.mapNotNull {
        it[FactDataStore.KEY_FACT_LENGTH]
    }

    val isLengthVisible: Flow<Boolean> = length.map {
        it > 100
    }

    val isShowMultipleCats: Flow<Boolean> = content.map {
        it.contains("cats")
    }

    fun fetchFact() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchFactUseCase()
            }.onSuccess {

            }.onFailure {

            }
        }
    }
}
