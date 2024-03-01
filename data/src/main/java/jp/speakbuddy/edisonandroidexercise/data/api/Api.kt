package jp.speakbuddy.edisonandroidexercise.data.api

import retrofit2.http.GET

interface Api {
    @GET("fact")
    suspend fun getFact(): FactResponse
}