package com.vixiloc.vcashiermobile.presentation.screens.category

import com.vixiloc.vcashiermobile.domain.model.categories.CategoriesResponseItem

data class CategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val categories: List<CategoriesResponseItem> = emptyList(),
    val categoryName: String = "",
    val categoryId: Int? = null,
    val success: String = "",
    val confirmationMessage: String = "",
    val searchQuery: String = ""
)