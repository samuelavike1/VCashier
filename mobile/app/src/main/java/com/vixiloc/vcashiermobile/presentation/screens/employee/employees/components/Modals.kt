package com.vixiloc.vcashiermobile.presentation.screens.employee.employees.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.vixiloc.vcashiermobile.presentation.components.FilledButton
import com.vixiloc.vcashiermobile.presentation.components.IconButton
import com.vixiloc.vcashiermobile.presentation.components.TextField
import com.vixiloc.vcashiermobile.presentation.components.VerticalSpacer
import com.vixiloc.vcashiermobile.presentation.screens.employee.employees.EmployeesEvent
import com.vixiloc.vcashiermobile.presentation.screens.employee.employees.EmployeesViewModel
import com.vixiloc.vcashiermobile.presentation.screens.employee.employees.InputName

@Composable
fun AddEmployeeDialog(modifier: Modifier = Modifier, viewModel: EmployeesViewModel) {
    val state = viewModel.state.value
    val onEvent = viewModel::onEvent
    Dialog(
        onDismissRequest = {
            onEvent(EmployeesEvent.ShowAddDialog(false))
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.medium
                )
                .verticalScroll(state = rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tambah Pegawai",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight(600)
                    )
                )

                IconButton(onClick = {
                    onEvent(EmployeesEvent.ShowAddDialog(false))
                }, icon = Icons.Outlined.Close)
            }
            VerticalSpacer(height = 10.dp)
            TextField(
                value = state.name,
                onValueChanged = {
                    onEvent(
                        EmployeesEvent.ChangeInput(
                            InputName.NAME,
                            it
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                title = "",
                placeHolder = "Masukkan name",
            )
            TextField(
                value = state.email,
                onValueChanged = {
                    onEvent(
                        EmployeesEvent.ChangeInput(
                            InputName.EMAIL,
                            it
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                title = "",
                placeHolder = "Masukkan email",
            )
            TextField(
                value = state.password,
                onValueChanged = {
                    onEvent(
                        EmployeesEvent.ChangeInput(
                            InputName.PASSWORD,
                            it
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                title = "",
                placeHolder = "Masukkan password",
                visualTransformation = PasswordVisualTransformation()
            )
            VerticalSpacer(height = 12.dp)

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Role",
                style = MaterialTheme.typography.bodySmall
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = state.role == "cashier",
                        onClick = {
                            onEvent(EmployeesEvent.ChangeInput(InputName.ROLE, "cashier"))
                        }
                    )
                    Text(text = "Kasir", style = MaterialTheme.typography.bodySmall)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = state.role == "warehouse",
                        onClick = {
                            onEvent(EmployeesEvent.ChangeInput(InputName.ROLE, "warehouse"))
                        }
                    )
                    Text(text = "Gudang", style = MaterialTheme.typography.bodySmall)
                }
            }
            VerticalSpacer(height = 32.dp)
            FilledButton(
                onClick = {
                    onEvent(EmployeesEvent.ShowAddDialog(false))
                    onEvent(EmployeesEvent.AddEmployee)
                },
                text = "Tambah",
                modifier = Modifier.fillMaxWidth(0.4f),
                textStyle = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight(600))
            )
        }
    }
}