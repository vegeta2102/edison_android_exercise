package jp.speakbuddy.edisonandroidexercise.ui

import androidx.datastore.preferences.core.preferencesOf
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.component.CoroutineExtension
import jp.speakbuddy.edisonandroidexercise.component.InstantExecutorExtension
import jp.speakbuddy.edisonandroidexercise.data.store.FactDataStore
import jp.speakbuddy.edisonandroidexercise.domain.usecase.FetchFactUseCase
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantExecutorExtension::class, CoroutineExtension::class)
class FactViewModelTest {

    private val factDataStore = mockk<FactDataStore>(relaxed = true)
    private val fetchFactUseCase = mockk<FetchFactUseCase>(relaxed = true)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @DisplayName("The content is within 100 characters. Expected length is not displayed on the UI")
    @Test
    fun case_001() = runTest {
        val preferences = preferencesOf(
            FactDataStore.KEY_FACT_CONTENT to "test", FactDataStore.KEY_FACT_LENGTH to 4
        )
        every {
            factDataStore.data.data
        } returns flowOf(preferences)
        val viewModel = FactViewModel(fetchFactUseCase, factDataStore)
        Assertions.assertTrue(viewModel.length.first() < 100)
        Assertions.assertFalse(viewModel.isLengthVisible.first())
    }

    @DisplayName("The content exceeds 100 characters. Expected length is displayed on the UI")
    @Test
    fun case_002() = runTest {
        val preferences = preferencesOf(
            FactDataStore.KEY_FACT_CONTENT to "1234567890-1234567890-1234567890-1234567890-1234567890-1234567890-1234567890-1234567890-1234567890-1234567890-1",
            FactDataStore.KEY_FACT_LENGTH to 101
        )
        every {
            factDataStore.data.data
        } returns flowOf(preferences)
        val viewModel = FactViewModel(fetchFactUseCase, factDataStore)
        Assertions.assertTrue(viewModel.length.first() > 100)
        Assertions.assertTrue(viewModel.isLengthVisible.first())
    }

    @DisplayName("The content does not contain any cats text. Expected multiple cats are not shown on the UI")
    @Test
    fun case_003() = runTest {
        val preferences = preferencesOf(
            FactDataStore.KEY_FACT_CONTENT to "dog dog dog",
            FactDataStore.KEY_FACT_LENGTH to 11
        )
        every {
            factDataStore.data.data
        } returns flowOf(preferences)
        val viewModel = FactViewModel(fetchFactUseCase, factDataStore)
        Assertions.assertFalse(viewModel.isShowMultipleCats.first())
    }

    @DisplayName("The content contains the cats text. Expected multiple cats are shown on the UI")
    @Test
    fun case_004() = runTest {
        val preferences = preferencesOf(
            FactDataStore.KEY_FACT_CONTENT to "I have two cats",
            FactDataStore.KEY_FACT_LENGTH to 11
        )
        every {
            factDataStore.data.data
        } returns flowOf(preferences)
        val viewModel = FactViewModel(fetchFactUseCase, factDataStore)
        Assertions.assertTrue(viewModel.isShowMultipleCats.first())
    }

}
