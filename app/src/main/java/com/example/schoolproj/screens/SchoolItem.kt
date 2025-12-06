package com.example.schoolproj.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schoolproj.model.SatResult
import com.example.schoolproj.model.School

@Composable
fun SchoolItem(school: School, sat: SatResult?, onClick: (String) -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable { onClick(school.dbn) }) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = school.schoolName ?: "Unknown school")
            Text(text = school.borough ?: "Unknown borough")
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "SAT Math: ${sat?.sat_math_avg_score ?: "N/A"}  Reading: ${sat?.sat_critical_reading_avg_score ?: "N/A"}  Writing: ${sat?.sat_writing_avg_score ?: "N/A"}"
            )
        }
    }
}