package com.example.sdkgit.di

import com.example.sdkgit.data.RetrofitInstanceGit
import com.example.sdkgit.data.repository.GitHubRepository
import com.example.sdkgit.ui.viewMocel.ViewModelGitHubAp
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { GitHubRepository() }
    viewModel { ViewModelGitHubAp(get()) }
    single { RetrofitInstanceGit }
}
