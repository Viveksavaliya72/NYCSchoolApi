package com.example.schoolproj.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun NYCNavigate( modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list"){
        composable("list"){
       SchoolListScreen(onClickSchool = {
               dbn -> navController.navigate("detail/$dbn")
       })
        }

        composable("detail/{dbn}",
            arguments = listOf(navArgument("dbn"){type = NavType.StringType})
            ){
            backStackEntry -> val dbn = backStackEntry.arguments?.getString("dbn")?:""
            SchoolDetailScreen(dbn=dbn, navBack = {navController.popBackStack()})
        }

    }

}