package com.vixiloc.vcashiermobile.presentation.widgets.transaction

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.vixiloc.vcashiermobile.presentation.widgets.utils.VerticalSpacer

@Composable
fun CashlessPayment() {
    var active by remember {
        mutableStateOf(false)
    }
    LazyColumn {
        items(5) {
            PaymentMethodItem(label = "Transfer Bank $it", onCLick = {
                active = !active
            }, active = active)
        }
        item {
            VerticalSpacer(height = 100.dp)
        }
    }
}