package com.pauloyr.searchrepository.data

import com.pauloyr.searchrepository.BuildConfig
import com.pauloyr.searchrepository.data.api.GitLab
import com.pauloyr.searchrepository.data.model.toRepository
import com.pauloyr.searchrepository.domain.Repository

class GitLabRepositoryImpl(
    private val service: GitLab
) : GitLabRepository {
    override suspend fun getRepositorys(search: String, page: Int): List<Repository> {
        val result = service.search("projects", search, page,BuildConfig.PRIVATE_TOKEN).parseResponse()
        return when (result) {
            is Output.Success -> {
                val categoryResponseList = result.value

                categoryResponseList.map {
                    it.toRepository()
                }
            }
            is Output.Failure -> throw GetRepositorysGitLabExpeciton()
        }
    }
}

interface GitLabRepository {
    suspend fun getRepositorys(search: String, page: Int): List<Repository>
}

class GetRepositorysGitLabExpeciton : Exception()