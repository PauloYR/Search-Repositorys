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
    private val _repository = MutableLiveData<List<Repository>>()
    val repository = _repository as LiveData<List<Repository>>

    fun getRepository(search: String, page: Int) {
        viewModelScope.launch {
            val listGitLab = gitLabRepository.getRepositorys(search, page)
            val listGibHub = gitHubRepository.getRepositorys(search, page)

            val list: MutableList<Repository> = ArrayList()
            list.addAll(listGitLab)
            list.addAll(listGibHub)

            withContext(Dispatchers.Main) {
                _repository.value = list.toList()
            }
        }
    }
}