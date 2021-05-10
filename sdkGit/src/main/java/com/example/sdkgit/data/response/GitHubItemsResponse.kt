package com.example.sdkgit.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubItemsResponse(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "full_name")
    val full_name: String = "",
    @Json(name = "stargazers_count")
    val stargazers_count: Int = 0,
    @Json(name = "forks_count")
    val forks_count: Int = 0,
    @Json(name = "owner")
    val owner : GitHubOwnerResponse


)