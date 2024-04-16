package com.rajat.assignment.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostsItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable