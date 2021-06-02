package com.pauloyr.searchrepository

import com.pauloyr.searchrepository.data.GitLabRepository
import com.pauloyr.searchrepository.data.GitLabRepositoryImpl
import com.pauloyr.searchrepository.data.api.GitLab
import com.pauloyr.searchrepository.data.network.Service
import com.pauloyr.searchrepository.presenter.MainViewModel
import org.koin.dsl.module

val mealServiceModule = module {

    single { Service().buildApi(GitLab::class.java, get(), "") }

    single { GitLabRepositoryImpl(get()) }

    single { MainViewModel(get()) }
}