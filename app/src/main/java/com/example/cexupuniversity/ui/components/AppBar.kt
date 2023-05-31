package com.example.cexupuniversity.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier, title: String, action : @Composable () -> Unit = {}){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = title, style = MaterialTheme.typography.headlineMedium)
        },
        actions = {
            action()
        }
    ) 
}