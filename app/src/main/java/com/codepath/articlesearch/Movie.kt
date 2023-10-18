package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class SearchNewsResultsMovie(
    @SerialName("results")
    val results: List<Movie>?
)



@Keep
@Serializable
data class Movie(

    @SerialName("title")
    val title: String?,

    @SerialName("popularity")
    val popularity: String?,

    @SerialName("backdrop_path")
    val imageUrl: String?,

    @SerialName("release_date")
    val releaseDate: String?,

) : java.io.Serializable
