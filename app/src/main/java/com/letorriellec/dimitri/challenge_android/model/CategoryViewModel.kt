package com.letorriellec.dimitri.challenge_android.model

import java.io.Serializable

data class CategoryViewModel(
    val id: String,
    val resourceUri: String,
    val resourceType: String,
    val name: String,
    val parent: ParentViewModel?,
    val custom: Boolean,
    val other: Boolean,
    val isDeleted: Boolean
) : Serializable
