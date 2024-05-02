package com.vixiloc.vcashiermobile.di

import com.vixiloc.vcashiermobile.domain.use_case.CreateCategory
import com.vixiloc.vcashiermobile.domain.use_case.GetCategories
import com.vixiloc.vcashiermobile.domain.use_case.GetProducts
import com.vixiloc.vcashiermobile.domain.use_case.GetToken
import com.vixiloc.vcashiermobile.domain.use_case.Login
import com.vixiloc.vcashiermobile.presentation.screens.login.LoginViewModel
import com.vixiloc.vcashiermobile.presentation.screens.transaction.TransactionViewModel
import com.vixiloc.vcashiermobile.presentation.screens.welcome.WelcomeViewModel
import com.vixiloc.vcashiermobile.presentation.screens.category.CategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { provideLoginViewModel(get()) }
    viewModel { provideWelcomeViewModel(get()) }
    viewModel { provideTransactionViewModel(get()) }
    viewModel { provideCategoryViewModel(get(), get()) }
}

fun provideLoginViewModel(login: Login): LoginViewModel {
    return LoginViewModel(login = login)
}

fun provideWelcomeViewModel(getToken: GetToken): WelcomeViewModel {
    return WelcomeViewModel(getToken = getToken)
}

fun provideTransactionViewModel(getProducts: GetProducts): TransactionViewModel {
    return TransactionViewModel(getProductsUseCase = getProducts)
}

fun provideCategoryViewModel(
    getCategories: GetCategories,
    createCategory: CreateCategory
): CategoryViewModel {
    return CategoryViewModel(getCategories = getCategories, createCategory = createCategory)
}