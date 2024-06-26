package com.vixiloc.vcashiermobile.presentation.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.vixiloc.vcashiermobile.presentation.components.AlertType
import com.vixiloc.vcashiermobile.presentation.components.Loading
import com.vixiloc.vcashiermobile.presentation.components.MessageAlert
import com.vixiloc.vcashiermobile.presentation.navs.routes.MainRoutes
import org.koin.androidx.compose.koinViewModel

@Composable
fun UpdateCategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = koinViewModel(),
    navHostController: NavHostController,
    navArgs: MainRoutes.NavDrawerScreens.Categories.UpdateCategory
) {
    val state = viewModel.state
    val events = viewModel::onEvent
    LaunchedEffect(key1 = Unit) {
        events(
            CategoryEvent.PreFillFormData(
                id = navArgs.id,
                name = navArgs.name
            )
        )
    }

    Column(modifier = modifier) {
        CategoryForm(viewModel = viewModel, type = FormType.UPDATE)

        MessageAlert(
            type = AlertType.SUCCESS,
            message = state.success,
            title = "Sukses",
            modifier = Modifier,
            visible = state.success.isNotEmpty(),
            onDismiss = {
                events(CategoryEvent.DismissAlertMessage)
                navHostController.navigateUp()
            }
        )

        MessageAlert(
            type = AlertType.ERROR,
            message = state.error,
            title = "Error",
            modifier = Modifier,
            visible = state.error.isNotEmpty(),
            onDismiss = {
                events(CategoryEvent.DismissAlertMessage)
            }
        )

        Loading(modifier = Modifier, visible = state.isLoading)
    }
}