package com.candela.lift

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardRutina() {
    var text by rememberSaveable { mutableStateOf("") }

    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = {text = it},
                    label = { Text(text = "Nombre Ejercicio") },
                    modifier = Modifier.padding(10.dp)
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = false, onDismissRequest = { }
                ) {
                    DropdownMenuItem(text = { Text(text = "Eliminar") }, onClick = {})
                }
            }
            HorizontalDivider(thickness = 2.dp)
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Series", fontWeight = FontWeight.Bold)
                    Text("1")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Kg", fontWeight = FontWeight.Bold)

                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Repeticiones", fontWeight = FontWeight.Bold)
                }
            }
            Button(
                onClick = {},
                colors = ButtonColors(
                    containerColor = Color(0xFF8F8E8E),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF8F8E8E),
                    disabledContentColor = Color(0xFF8F8E8E)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "+ Agregar Serie",
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .background(color = Color(0xFF8F8E8E))
                )
            }
        }
    }
}