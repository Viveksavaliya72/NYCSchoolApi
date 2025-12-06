package com.example.schoolproj.screens


import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schoolproj.repositories.SchoolWithSat
import com.example.schoolproj.viewModel.SchoolViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolListScreen(onClickSchool: (String) -> Unit, viewModel: SchoolViewModel = hiltViewModel()){

    val state by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("NYC School List") },
            )}
    ){it->
        when(state){

            is SchoolViewModel.UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }

            is SchoolViewModel.UiState.Error -> {
                Text(text = (state as SchoolViewModel.UiState.Error).message)
            }

            is SchoolViewModel.UiState.Success -> {
                val list : List<SchoolWithSat> = (state as SchoolViewModel.UiState.Success).schools
                LazyColumn(modifier = Modifier.padding(it).padding(10.dp)) {
                    items(list){
                            item ->
                        SchoolItem(
                            school = item.school,
                            sat = item.sat,
                            onClick = onClickSchool
                        )
                    }
                }
            }
        }
    }

}