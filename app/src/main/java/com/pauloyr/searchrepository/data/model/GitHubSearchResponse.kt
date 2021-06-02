package com.pauloyr.searchrepository.data.model

import com.google.gson.annotations.SerializedName
import com.pauloyr.searchrepository.domain.Repository
import java.util.*

data class GitHubSearchResponse(
    @SerializedName("items")
   val list: List<RepositoryGitHub>
)

data class RepositoryGitHub(
    val description: String,
    val name: String,
    @SerializedName("last_activity_at")
    val lastActivityAt: String,
    @SerializedName("forks_count")
    val forks: Int,
    @SerializedName("stargazers_count")
    val start: Int
)

fun RepositoryGitHub.toRepository() : Repository {
    return Repository(description,name, Date(),forks,start)
}