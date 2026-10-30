package com.example.t1diary.ui.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.t1diary.R
import com.example.t1diary.ui.theme.T1DiaryTheme

@Composable
fun SettingsScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.placeholder_settings),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    T1DiaryTheme {
        SettingsScreen()
    }
}