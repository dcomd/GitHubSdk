package com.example.sdkgit.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TbGitHubb")
data class GitHubModelDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "full_name") var full_name: String = "",
    @ColumnInfo(name = "stargazers_count") var stargazers_count: Int = 0,
    @ColumnInfo(name = "forks_count") var forks_count: Int = 0,
    @ColumnInfo(name = "login") var login: String = "",
    @ColumnInfo(name = "avatar_url") var avatar_url: String = ""
)
