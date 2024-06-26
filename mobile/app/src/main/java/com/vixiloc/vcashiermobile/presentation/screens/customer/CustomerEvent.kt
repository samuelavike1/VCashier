package com.vixiloc.vcashiermobile.presentation.screens.customer

import com.vixiloc.vcashiermobile.domain.model.customers.CustomerResponseItem

sealed class CustomerEvent {
    data object DismissAlertMessage : CustomerEvent()
    data class InputCustomerName(val name: String) : CustomerEvent()
    data class InputCustomerNumber(val number: String) : CustomerEvent()
    data object SubmitCreateCustomer : CustomerEvent()
    data object SubmitUpdateCustomer : CustomerEvent()
    data class PreFillFormData(val id: Int?, val name: String, val number: String?) : CustomerEvent()
    data class DeleteCustomer(val data: CustomerResponseItem) : CustomerEvent()
    data object ProcessDeleteCustomer : CustomerEvent()
    data class InputSearchValue(val query: String) : CustomerEvent()
}