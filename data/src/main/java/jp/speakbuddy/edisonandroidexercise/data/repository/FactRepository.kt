package jp.speakbuddy.edisonandroidexercise.data.repository

import jp.speakbuddy.edisonandroidexercise.data.api.FactResponse

interface FactRepository {
    suspend fun fetch(): FactResponse
}