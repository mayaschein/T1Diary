package com.example.t1diary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.t1diary.ui.T1DiaryApp
import com.example.t1diary.ui.theme.T1DiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T1DiaryTheme {
                T1DiaryApp()
            }
        }
    }
}