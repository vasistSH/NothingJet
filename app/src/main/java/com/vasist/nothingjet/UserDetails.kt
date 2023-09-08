package com.vasist.nothingjet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vasist.nothingjet.module.UserList

@Composable
fun AlertDialogExample(
    user: UserList,
    showDialog: Boolean,
    onCloseDialog: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = {
                onCloseDialog()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Show Alert Dialog")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    onCloseDialog()
                },
                title = {
                    Text("${user.name.title} ${user.name.first} ${user.name.last}")
                },
                text = {
                    Text("Phone: ${user.phone}\n" +
                            "Gender: ${user.gender}\n" +
                    "")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onCloseDialog()
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            onCloseDialog()
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}







