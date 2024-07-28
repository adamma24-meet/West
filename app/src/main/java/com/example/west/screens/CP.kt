package com.example.west.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.west.R
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SurveyApp()
        }
    }
}

@Composable
fun SurveyApp() {
    SurveyScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyScreen() {
    var updateChoice by remember { mutableStateOf("A new checkpoint") }
    var location by remember { mutableStateOf("") }
    var otherDetails by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("What do you want to update?", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        val options = listOf("A new checkpoint", "The status of a checkpoint", "Other")

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = updateChoice,
                onValueChange = { },
                readOnly = true,
                label = { Text("Update Choice") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                },
                modifier = Modifier.menuAnchor()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick =
                        { updateChoice = option
                            ; expanded = false}
                }
                        Text(text = option))
                        }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (updateChoice == "Other") {
            TextField(
                value = otherDetails,
                onValueChange = { otherDetails = it },
                label = { Text("Additional Details (if any)") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Handle submit action
                // For demonstration, using print statements.
                // Consider using a logger or handling submission properly.
                println("Submit clicked")
                println("Update Choice: $updateChoice")
                println("Location: $location")
                if (updateChoice == "Other") {
                    println("Other Details: $otherDetails")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSurveyScreen() {
    SurveyAppTheme {
        SurveyScreen()
    }
}
}

