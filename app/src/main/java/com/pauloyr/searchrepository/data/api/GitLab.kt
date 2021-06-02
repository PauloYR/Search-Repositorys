package com.pauloyr.searchrepository.data.api

import com.pauloyr.searchrepository.data.model.GitLabSearchResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface GitLab {
    @GET("search")
    suspend fun search(
        @Query("scope") scope: String,
        @Query("search") search: String,
        @Query("page") page: Int,
    ): Response<List<GitLabSearchResponse>>
}