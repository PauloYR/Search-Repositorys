package com.pauloyr.searchrepository.data

import com.pauloyr.searchrepository.data.api.GitHub
import com.pauloyr.searchrepository.data.model.toRepository
import com.pauloyr.searchrepository.domain.Repository


class GitHubRepositoryImpl (
    private val service: GitHub
        ): GitHubRepository{
    override suspend fun getRepositorys(search:String,page: Int): List<Repository> {
        val result = service.search(search,page).parseResponse()
        return when (result) {
            is Output.Success -> {
                val categoryResponseList = result.value.list

                categoryResponseList.map {
                    it.toRepository()
                }
            }
            is Output.Failure -> throw GetRepositorysGitHubExpeciton()
        }
    }
}

interface  GitHubRepository{
    suspend fun getRepositorys(search:String,page: Int): List<Repository>
}

class GetRepositorysGitHubExpeciton : Exception()