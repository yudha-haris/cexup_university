package com.example.cexupuniversity.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.cexupuniversity.data.model.Course
import com.example.cexupuniversity.helper.InitialDataSource
import com.example.cexupuniversity.service.ViewModelFactory
import com.example.cexupuniversity.ui.common.UiState
import com.example.cexupuniversity.ui.components.AppBar
import com.example.cexupuniversity.ui.navigation.Screen
import com.example.cexupuniversity.ui.theme.CexupUniversityTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    navController: NavHostController,
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { data ->
        Scaffold(
            topBar = {
                AppBar(title = "CeXup University")
            }
        ) {innerPadding ->
            when(data){
                is UiState.Loading -> {
                    CircularProgressIndicator()
                    viewModel.getAllCourses()
                }
                is UiState.Success -> {
                    val courses = data.data

                    if(courses.isEmpty()){
                        EmptyListView(onRefresh = {
                            viewModel.getData()
                        })
                    } else {
                        LazyColumn(
                            modifier = modifier,
                            contentPadding = innerPadding
                        ){
                            items(courses.size, key = {courses[it].courseId}){
                                CourseItem(course = courses[it],
                                    modifier = Modifier
                                        .padding(vertical = 4.dp, horizontal = 16.dp)
                                        .clickable {
                                            navController.navigate(Screen.Detail.createRoute(courses[it].courseId))
                                        }
                                )
                            }
                        }
                    }

                }
                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun CourseItem(
    course: Course,
    modifier: Modifier = Modifier
    ){
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = course.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Kode Mata Kuliah: ${course.courseId}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}

@Composable
fun EmptyListView(
    onRefresh: () -> Unit,
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(text = "Data Tidak Ditemukan")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRefresh) {
            Text(text = "Ambil Data", color = Color.White)
        }
    }
}

@Composable
@Preview
fun CourseItemPreview(){
    CexupUniversityTheme {
        Scaffold(
            topBar = {
                AppBar(title = "CeXup University")
            }
        ) {innerPadding ->
            val courses = InitialDataSource.getCourses()

            LazyColumn(
                contentPadding = innerPadding
            ){
                items(courses.size, key = {courses[it].courseId}){
                    CourseItem(course = courses[it], modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp))
                }
            }
        }

    }
}