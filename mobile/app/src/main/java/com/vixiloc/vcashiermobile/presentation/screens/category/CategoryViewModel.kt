package com.vixiloc.vcashiermobile.presentation.screens.category

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vcashiermobile.commons.Resource
import com.vixiloc.vcashiermobile.commons.Strings.TAG
import com.vixiloc.vcashiermobile.domain.model.CreateCategoryRequest
import com.vixiloc.vcashiermobile.domain.use_case.CreateCategory
import com.vixiloc.vcashiermobile.domain.use_case.GetCategories
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CategoryViewModel(
    private val getCategories: GetCategories,
    private val createCategory: CreateCategory
) : ViewModel() {

    var state by mutableStateOf(CategoryState())

    fun onEvent(e: CategoryEvent) {
        when (e) {
            is CategoryEvent.DismissAlertMessage -> {
                state = state.copy(error = "", success = "")
            }

            is CategoryEvent.InputCategoryName -> {
                state = state.copy(categoryName = e.name)
            }

            is CategoryEvent.SubmitCreateCategory -> {
                submitCreateCategory()
            }
        }
    }

    private fun submitCreateCategory() {
        createCategory(data = CreateCategoryRequest(name = state.categoryName)).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = resource.message ?: "An error unexpected!",
                        isLoading = false
                    )
                }

                is Resource.Success -> {
                    state =
                        state.copy(isLoading = false, success = resource.data?.message ?: "Success")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllCategories() {
        Log.d(TAG, "getAllCategories: called")
        getCategories().onEach { resource ->
            Log.d(TAG, "getAllCategories: ${resource.data}")
            when (resource) {
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = resource.message ?: "An error unexpected!",
                        isLoading = false
                    )
                }

                is Resource.Success -> {
                    Log.d(TAG, "getAllCategories: ${resource.data}")
                    state = state.copy(isLoading = false, categories = resource.data ?: emptyList())
                }

            }
        }.launchIn(viewModelScope)
    }
}