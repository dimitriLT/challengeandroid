package com.letorriellec.dimitri.challenge_android.model

import java.io.Serializable

data class ParentViewModel(
    val id: String,
    val resourceType: String,
    val resourceUri: String
) : Serializable