package com.vixiloc.vcashiermobile.presentation.screens.transaction.create_transaction

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.vixiloc.vcashiermobile.commons.CurrencyFormatter
import com.vixiloc.vcashiermobile.presentation.CheckoutActivity
import com.vixiloc.vcashiermobile.presentation.components.commons.AlertType
import com.vixiloc.vcashiermobile.presentation.components.commons.FloatingTransactionButton
import com.vixiloc.vcashiermobile.presentation.components.commons.Loading
import com.vixiloc.vcashiermobile.presentation.components.commons.MessageAlert
import com.vixiloc.vcashiermobile.presentation.components.commons.SearchTextField
import com.vixiloc.vcashiermobile.presentation.components.commons.VerticalSpacer
import com.vixiloc.vcashiermobile.presentation.components.products.TransactionProductItem
import com.vixiloc.vcashiermobile.presentation.components.transaction.AddToCartModal
import com.vixiloc.vcashiermobile.presentation.navigations.Screens
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateTransactionScreen(
    navigator: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CreateTransactionViewModel = koinViewModel()
) {
    val state = viewModel.state.value
    val onEvent = viewModel::onEvent
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {

        VerticalSpacer(height = 24.dp)
        SearchTextField(
            value = "Cari",
            onValueChanged = {},
            modifier = Modifier,
            textStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground)
        )
        VerticalSpacer(height = 37.dp)
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (products, cartInfo) = createRefs()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .constrainAs(products) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                        height = Dimension.matchParent
                    },
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.products) { product ->
                    TransactionProductItem(
                        name = product.name,
                        image = product.imageUrl ?: "",
                        onAdd = {
                            onEvent(CreateTransactionEvent.AddVariation(it, product))
                        },
                        modifier = Modifier,
                        variations = product.variations
                    )
                }
                item {
                    VerticalSpacer(height = 100.dp)
                }
            }

            FloatingTransactionButton(
                onClick = {
                    context.startActivity(Intent(context, CheckoutActivity::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(cartInfo) {
                        start.linkTo(products.start)
                        end.linkTo(products.end)
                        bottom.linkTo(products.bottom, margin = 24.dp)
                    },
                textStart = "${state.cartItems.count()} Item",
                textEnd = CurrencyFormatter.formatCurrency(state.cartItems.sumOf { it.price }),
                icon = Icons.Outlined.ShoppingCart
            )
        }

        Loading(modifier = Modifier, visible = state.isLoading)

        MessageAlert(
            type = AlertType.SUCCESS,
            message = state.success,
            title = "Sukses",
            modifier = Modifier,
            visible = state.success.isNotEmpty(),
            onDismiss = {
                onEvent(CreateTransactionEvent.DismissAlertMessage)
            }
        )
        MessageAlert(
            type = AlertType.ERROR,
            message = state.error,
            title = "Error",
            modifier = Modifier,
            visible = state.error.isNotEmpty(),
            onDismiss = {
                onEvent(CreateTransactionEvent.DismissAlertMessage)
            }
        )

        state.selectedVariation?.let {
            if (state.selectedProduct != null) {
                AnimatedVisibility(visible = true) {
                    AddToCartModal(
                        variation = it,
                        onDismissRequest = {
                            onEvent(CreateTransactionEvent.DismissAddToCartModal)
                        },
                        product = state.selectedProduct,
                        onConfirm = {
                            onEvent(CreateTransactionEvent.AddToCart(it))
                        }
                    )
                }
            }
        }
    }
}