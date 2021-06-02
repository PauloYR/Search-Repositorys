package com.pauloyr.searchrepository.data.api

import com.pauloyr.searchrepository.data.model.GitHubSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface GitHub {
    @GET("search/repositories")
    suspend fun search(
        @Query("q") search: String,
        @Query("page") page: Int
    ): Response<GitHubSearchResponse>
}