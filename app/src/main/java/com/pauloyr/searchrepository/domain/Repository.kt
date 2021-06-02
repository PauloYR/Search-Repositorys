package com.pauloyr.searchrepository.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class Repository(
    val description: String,
    val name: String,
    val lastActivityAt: Date,
    val forks: Int,
    val start: Int,
)
