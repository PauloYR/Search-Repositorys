package com.pauloyr.searchrepository

import com.pauloyr.searchrepository.data.GitHubRepository
import com.pauloyr.searchrepository.data.GitHubRepositoryImpl
import com.pauloyr.searchrepository.data.GitLabRepository
import com.pauloyr.searchrepository.data.GitLabRepositoryImpl
import com.pauloyr.searchrepository.data.api.GitHub
import com.pauloyr.searchrepository.data.api.GitLab
import com.pauloyr.searchrepository.data.network.Service
import com.pauloyr.searchrepository.presenter.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchServiceModule = module {

    single { Service().buildApi(GitLab::class.java, get(), "https://gitlab.com/api/v4/") }

    single { Service().buildApi(GitHub::class.java, get(), "https://api.github.com") }

    single<GitLabRepository>{ GitLabRepositoryImpl(get()) }

    single<GitHubRepository> { GitHubRepositoryImpl(get()) }

    viewModel { MainViewModel(gitHubRepository =  get(), gitLabRepository =  get()) }
}