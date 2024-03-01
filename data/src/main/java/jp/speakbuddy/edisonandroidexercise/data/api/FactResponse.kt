package jp.speakbuddy.edisonandroidexercise.data.api

import java.io.Serializable

data class FactResponse(
    val fact: String,
    val length: Int
) : Serializable