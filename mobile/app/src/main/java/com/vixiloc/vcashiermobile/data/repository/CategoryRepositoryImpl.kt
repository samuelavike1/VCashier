package com.vixiloc.vcashiermobile.data.repository

import com.vixiloc.vcashiermobile.data.remote.ApiService
import com.vixiloc.vcashiermobile.data.remote.dto.categories.CategoriesResponseDto
import com.vixiloc.vcashiermobile.data.remote.dto.categories.CreateUpdateCategoryRequestDto
import com.vixiloc.vcashiermobile.data.remote.dto.categories.CreateUpdateCategoryResponseDto
import com.vixiloc.vcashiermobile.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val api: ApiService) : CategoryRepository {
    override suspend fun getCategories(token: String): CategoriesResponseDto {
        return api.getCategories("Bearer $token")
    }

    override suspend fun createCategory(
        token: String,
        data: CreateUpdateCategoryRequestDto
    ): CreateUpdateCategoryResponseDto {
        return api.createCategory(data = data, token = "Bearer $token")
    }

    override suspend fun updateCategory(
        token: String,
        data: CreateUpdateCategoryRequestDto
    ): CreateUpdateCategoryResponseDto {
        return api.updateCategory(data = data, token = "Bearer $token")
    }
}