package com.pauloyr.searchrepository.data

import com.pauloyr.searchrepository.data.api.GitLab
import com.pauloyr.searchrepository.data.model.toRepository
import com.pauloyr.searchrepository.domain.Repository

class GitLabRepositoryImpl (
    private val service: GitLab
        ): GitLabRepository{
    override suspend fun getRepositorys(scope: String,search:String,page: Int): List<Repository> {
        val result = service.search(scope,search,page).body()
        return result!!.map {
            it.toRepository()
        }
    }
}

interface  GitLabRepository{
    suspend fun getRepositorys(scope: String,search:String,page: Int): List<Repository>
}