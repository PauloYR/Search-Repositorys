package com.pauloyr.searchrepository.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pauloyr.searchrepository.data.GitHubRepository
import com.pauloyr.searchrepository.data.GitLabRepository
import com.pauloyr.searchrepository.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val gitLabRepository: GitLabRepository,
    private val gitHubRepository: GitHubRepository
) : ViewModel() {
    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories = _repositories as LiveData<List<Repository>>

    private var search: String = ""

    private var page: Int = 0

    fun setSearch(search: String) {
        this.search = search
    }

    fun setPage(page: Int) {
        this.page = page
    }

    fun getRepositories() {
        viewModelScope.launch {
            val list = mountListRepositories()
            withContext(Dispatchers.Main) {
                _repositories.value = list
            }
        }
    }

    private suspend fun mountListRepositories(): List<Repository> {
        val repositoriesGitLab = gitLabRepository.getRepositorys(search, page)
        val repositoriesGitHub = gitHubRepository.getRepositorys(search, page)

        val listRepositories: MutableList<Repository> = ArrayList()
        _repositories.value?.let {
            listRepositories.addAll(it)
        }
        listRepositories.addAll(repositoriesGitLab)
        listRepositories.addAll(repositoriesGitHub)
        return listRepositories.toList()
    }

}