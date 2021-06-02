package com.pauloyr.searchrepository.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pauloyr.searchrepository.data.GitLabRepository
import com.pauloyr.searchrepository.domain.Repository
import kotlinx.coroutines.launch

class MainViewModel(val gitLabRepository: GitLabRepository) : ViewModel() {
    private val _repository = MutableLiveData<List<Repository>>()
    val repository = _repository as LiveData<List<Repository>>

    fun getRepository(search: String,page: Int){
        viewModelScope.launch {
            val listGitLab = gitLabRepository.getRepositorys("projects",search,page)
            _repository.value = listGitLab.map { gitlab ->
                gitlab
            }
        }
    }
}