package com.example.sdkgit.data.repository

import com.example.sdkgit.data.model.GitHubModel
import com.example.sdkgit.data.model.GitHubModelDb

class FactoryModel {

    fun factory(gitHubModel: List<GitHubModel>): List<GitHubModelDb> {
        val list: MutableList<GitHubModelDb> = mutableListOf()

        for (item in gitHubModel) {

            val gitHubModelDb = GitHubModelDb(

                name = item.name,
                full_name = item.full_name,
                stargazers_count = item.stargazers_count,
                forks_count = item.forks_count,
                login = item.gitHubOwner.login,
                avatar_url = item.gitHubOwner.avatar_url

            )

            list.add(gitHubModelDb)
        }
        return list
    }
}