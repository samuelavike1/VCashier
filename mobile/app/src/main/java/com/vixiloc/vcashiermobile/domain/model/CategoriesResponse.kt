package com.vixiloc.vcashiermobile.domain.model

data class CategoriesResponse(
    val categoriesResponseDto: List<CategoriesResponseItem>
)

data class CategoriesResponseItem(
    val name: String,
    val id: Int
)