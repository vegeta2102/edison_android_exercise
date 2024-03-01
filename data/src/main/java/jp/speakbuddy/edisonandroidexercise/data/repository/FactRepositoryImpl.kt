package jp.speakbuddy.edisonandroidexercise.data.repository

import jp.speakbuddy.edisonandroidexercise.data.api.Api
import jp.speakbuddy.edisonandroidexercise.data.api.FactResponse
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(val api: Api) : FactRepository {
    override suspend fun fetch(): FactResponse {
        return api.getFact()
    }
}