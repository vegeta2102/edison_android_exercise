package jp.speakbuddy.edisonandroidexercise.domain

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.component.CoroutineExtension
import jp.speakbuddy.edisonandroidexercise.component.InstantExecutorExtension
import jp.speakbuddy.edisonandroidexercise.data.api.FactResponse
import jp.speakbuddy.edisonandroidexercise.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.data.store.FactDataStore
import jp.speakbuddy.edisonandroidexercise.domain.usecase.FetchFactUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalTime
@ExtendWith(InstantExecutorExtension::class, CoroutineExtension::class)
class FetchFactUseCaseTest {
    private val factRepository = mockk<FactRepository>(relaxed = true)
    private val factDataStore = mockk<FactDataStore>(relaxed = true)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @DisplayName("The api has a problem. Expected throw the exception")
    @Test
    fun case_001() = runTest {
        val useCase = FetchFactUseCase(factRepository, factDataStore)
        coEvery {
            factRepository.fetch()
        } throws Exception("Server not found")
        assertThrows<Exception> {
            runBlocking {
                useCase.invoke()
            }
        }
    }

    @DisplayName("The api has no problem. Expected success response")
    @Test
    fun case_002() = runTest {
        val useCase = FetchFactUseCase(factRepository, factDataStore)
        coEvery {
            factRepository.fetch()
        } returns FactResponse("test", 4)
        assertDoesNotThrow {
            runBlocking {
                useCase.invoke()
            }
        }
    }
}
