package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class SearchNewsResultsCelebrity(
    @SerialName("results")
    val results: List<Celebrity>?
)



@Keep
@Serializable
data class Celebrity(

    @SerialName("name")
    val name: String?,

    @SerialName("popularity")
    val popularity: String?,

    @SerialName("profile_path")
    val imageUrl: String?,

    @SerialName("known_for_department")
    val knownFor: String?,

    ) : java.io.Serializable
