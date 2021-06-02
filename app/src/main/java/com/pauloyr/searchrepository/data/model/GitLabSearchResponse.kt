package com.pauloyr.searchrepository.data.model

import com.google.gson.annotations.SerializedName
import com.pauloyr.searchrepository.domain.Repository
import java.util.*

data class GitLabSearchResponse(
    val description: String,
    val name: String,
    @SerializedName("last_activity_at")
    val lastActivityAt: String,
    @SerializedName("forks_count")
    val forks: Int,
    @SerializedName("star_count")
    val start: Int
)

fun GitLabSearchResponse.toRepository() : Repository {
    return Repository(description,name, Date(),forks,start)
}