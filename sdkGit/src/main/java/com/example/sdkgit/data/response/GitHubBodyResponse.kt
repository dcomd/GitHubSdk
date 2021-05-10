package com.example.sdkgit.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
 data class GitHubBodyResponse (

    @Json(name = "items")
     val items : List<GitHubItemsResponse>

 )