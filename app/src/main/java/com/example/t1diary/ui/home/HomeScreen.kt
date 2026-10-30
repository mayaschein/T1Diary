package com.example.t1diary.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.t1diary.R
import com.example.t1diary.ui.theme.T1DiaryTheme

@Composable
fun HomeScreen(
    onLogbookClick: () -> Unit,
    onFoodDatabaseClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.placeholder_home),
                style = MaterialTheme.typography.headlineMedium
            )
            HomeNavigationButton(stringResource(R.string.title_logbook), onLogbookClick)
            HomeNavigationButton(stringResource(R.string.title_food_database), onFoodDatabaseClick)
            HomeNavigationButton(stringResource(R.string.title_settings), onSettingsClick)
        }
    }
}

@Composable
private fun HomeNavigationButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text)
    }
}

@Composable
fun HomeFab() {
    var expanded by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimatedVisibility(visible = expanded) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ExtendedFloatingActionButton(
                    text = { Text(stringResource(R.string.add_note)) },
                    icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                    onClick = { expanded = false }
                )
                ExtendedFloatingActionButton(
                    text = { Text(stringResource(R.string.add_food)) },
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    onClick = { expanded = false }
                )
            }
        }
        FloatingActionButton(
            onClick = { expanded = !expanded }
        ) {
            Icon(
                imageVector = if (expanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = stringResource(
                    if (expanded) R.string.close_menu else R.string.open_menu
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    T1DiaryTheme {
        HomeScreen(onLogbookClick = {}, onFoodDatabaseClick = {}, onSettingsClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeFabPreview() {
    T1DiaryTheme {
        HomeFab()
    }
}