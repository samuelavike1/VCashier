package com.vixiloc.vcashiermobile.data.repository

import com.vixiloc.vcashiermobile.data.remote.ApiService
import com.vixiloc.vcashiermobile.data.remote.dto.CategoriesResponseDto
import com.vixiloc.vcashiermobile.data.remote.dto.CreateCategoryRequestDto
import com.vixiloc.vcashiermobile.data.remote.dto.CreateCategoryResponseDto
import com.vixiloc.vcashiermobile.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val api: ApiService) : CategoryRepository {
    override suspend fun getCategories(token: String): CategoriesResponseDto {
        return api.getCategories("Bearer $token")
    }

    override suspend fun createCategory(
        token: String,
        data: CreateCategoryRequestDto
    ): CreateCategoryResponseDto {
        return api.createCategory(data = data, token = "Bearer $token")
    }
}