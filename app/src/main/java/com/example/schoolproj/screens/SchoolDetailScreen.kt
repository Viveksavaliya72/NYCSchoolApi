package com.example.schoolproj.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schoolproj.viewModel.SchoolViewModel
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolDetailScreen(dbn:String, navBack:()-> Unit, viewModel: SchoolViewModel=hiltViewModel()){

    val state by viewModel.uiState.collectAsState()

    val item = (state as? SchoolViewModel.UiState.Success)?.schools?.firstOrNull{it.school.dbn == dbn}

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(item?.school?.schoolName?:"School Detail") },
                navigationIcon = { IconButton(onClick = navBack){ Icon(Icons.Default.ArrowBack, contentDescription = "Back") } }
            )}
    ) { innerPadding ->
        if (item == null) {
            Text("Details not available", modifier = Modifier.padding(16.dp))
        } else {
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                Text("Location: ${item.school.location ?: "N/A"}")
                Text(
                    "Borough: ${item.school.borough ?: "N/A"}",
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    "Website: ${item.school.website ?: "N/A"}",
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    "Overview: ${item.school.overview ?: "N/A"}",
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text("SAT Scores", modifier = Modifier.padding(top = 16.dp))
                Text("Math: ${item.sat?.sat_math_avg_score ?: "N/A"}")
                Text("Reading: ${item.sat?.sat_critical_reading_avg_score ?: "N/A"}")
                Text("Writing: ${item.sat?.sat_writing_avg_score ?: "N/A"}")
            }

        }

    }
}

