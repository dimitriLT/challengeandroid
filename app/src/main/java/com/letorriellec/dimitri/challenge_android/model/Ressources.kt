package com.letorriellec.dimitri.challenge_android.model

data class Ressources(
    val pagination: Pagination, val resources: List<Category>
)

data class Pagination(
    val next_uri: Any, val previous_uri: Any
)

data class Category(
    val custom: Boolean,
    val id: Int,
    val is_deleted: Boolean,
    val name: String,
    val other: Boolean,
    val parent: Parent?,
    val resource_type: String,
    val resource_uri: String
)

data class Parent(
    val id: Int?, val resource_type: String, val resource_uri: String
)