package com.vixiloc.vcashiermobile.di

import com.vixiloc.vcashiermobile.commons.HttpHandler
import com.vixiloc.vcashiermobile.domain.repository.AuthRepository
import com.vixiloc.vcashiermobile.domain.repository.CategoryRepository
import com.vixiloc.vcashiermobile.domain.repository.DataStoreRepository
import com.vixiloc.vcashiermobile.domain.repository.ProductsRepository
import com.vixiloc.vcashiermobile.domain.use_case.CreateCategory
import com.vixiloc.vcashiermobile.domain.use_case.GetCategories
import com.vixiloc.vcashiermobile.domain.use_case.GetProducts
import com.vixiloc.vcashiermobile.domain.use_case.GetToken
import com.vixiloc.vcashiermobile.domain.use_case.Login
import com.vixiloc.vcashiermobile.domain.use_case.SaveToken
import org.koin.dsl.module

val useCaseModule = module {
    single { provideHttpHandler() }
    single { provideLoginUseCase(get(), get(), get()) }
    single { provideGetTokenUseCase(get()) }
    single { provideSaveTokenUseCase(get()) }
    single { provideGetProductsUseCase(get(), get()) }
    single { provideGetCategoriesUseCase(get(), get()) }
    single { provideCreateCategoryUseCase(get(), get()) }
}

fun provideHttpHandler(): HttpHandler {
    return HttpHandler()
}

fun provideLoginUseCase(
    repository: AuthRepository,
    httpHandler: HttpHandler,
    saveToken: SaveToken
): Login {
    return Login(repository = repository, httpHandler = httpHandler, saveToken = saveToken)
}

fun provideGetTokenUseCase(repository: DataStoreRepository): GetToken {
    return GetToken(repository)
}

fun provideSaveTokenUseCase(repository: DataStoreRepository): SaveToken {
    return SaveToken(repository)
}

fun provideGetProductsUseCase(getToken: GetToken, repository: ProductsRepository): GetProducts {
    return GetProducts(getToken = getToken, repository = repository)
}

fun provideGetCategoriesUseCase(repository: CategoryRepository, getToken: GetToken): GetCategories {
    return GetCategories(repository, getToken = getToken)
}

fun provideCreateCategoryUseCase(
    repository: CategoryRepository,
    getToken: GetToken
): CreateCategory {
    return CreateCategory(repository, getToken = getToken)
}