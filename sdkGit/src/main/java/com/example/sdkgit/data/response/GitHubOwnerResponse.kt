package com.example.sdkgit.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubOwnerResponse (
    @Json(name = "login")
    val login: String = "",
    @Json(name = "avatar_url")
    val avatar_url: String = ""
)