package com.example.cexupuniversity.ui.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cexupuniversity.data.model.Course
import com.example.cexupuniversity.data.model.Dosen
import com.example.cexupuniversity.data.model.Mahasiswa
import com.example.cexupuniversity.helper.InitialDataSource
import com.example.cexupuniversity.service.ViewModelFactory
import com.example.cexupuniversity.ui.common.UiState
import com.example.cexupuniversity.ui.components.AppBar
import com.example.cexupuniversity.ui.theme.CexupUniversityTheme

@Composable
fun DetailScreen(
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
){
    val query by viewModel.query
    val courseAndDosen by viewModel.courseAndDosen
    val dosen : Dosen = courseAndDosen.dosen
    val matakuliah : Course = courseAndDosen.course

    viewModel.uiStateMahasiswa.collectAsState(initial = UiState.Loading).value.let { data ->
        when(data){
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
                viewModel.getAllMahasiswaWithCourseId(id)
                viewModel.getAllDosenWithCourseId(id)
            }
            is UiState.Success -> {
                val mahasiswa = data.data

                Scaffold(
                    modifier = modifier,
                    topBar = {
                        AppBar(title = matakuliah.name)
                    }
                ) {innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Dosen",
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )

                        HeaderCourse(dosen = dosen)

                        Text(
                            text = "Mahasiswa",
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )

                        com.example.cexupuniversity.ui.components.SearchBar(
                            query = query,
                            onQueryChange = {
                                            viewModel.getAllMahasiswaByName(id, it)
                            },
                            placeholder = "Cari Mahasiswa",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        LazyColumn{
                            items(mahasiswa.size, key = {mahasiswa[it].nim}){
                                MahasiswaItem(mahasiswa = mahasiswa[it],
                                    modifier = Modifier
                                        .padding(vertical = 4.dp),
                                    onDelete = {
                                        viewModel.deleteMahasiswa(mahasiswa[it])
                                        viewModel.getAllMahasiswaWithCourseId(id)
                                    }
                                )
                            }
                        }
                    }
                }


            }
            is UiState.Error -> {}
        }

    }
}

@Composable
fun HeaderCourse(
    dosen: Dosen,
    modifier: Modifier = Modifier,
){
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = dosen.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "NID: ${dosen.nid}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}

@Composable
fun MahasiswaItem(
    mahasiswa: Mahasiswa,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
){
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ){
        Row(modifier = Modifier.padding(8.dp)) {
            Column(
                modifier = Modifier.padding(8.dp).weight(weight = 1.0f, fill = true)
            ) {
                Text(
                    text = mahasiswa.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "NIM: ${mahasiswa.nim}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Box(
                modifier = Modifier.align(alignment = Alignment.CenterVertically).
                clickable {
                    onDelete()
                }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Button")
            }
        }
        
    }
}

@Composable
@Preview
fun DetailPreview(){
    CexupUniversityTheme() {
        val mahasiswa = InitialDataSource.getMahasiswa()
        val dosen = InitialDataSource.getDosen()[0]
        val matakuliah = InitialDataSource.getCourses()[0]

        Scaffold(
            topBar = {
                AppBar(title = matakuliah.name)
            }
        ) {innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Dosen",
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )

                HeaderCourse(dosen = dosen,)

                Text(
                    text = "Mahasiswa",
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                com.example.cexupuniversity.ui.components.SearchBar(
                    query = "",
                    onQueryChange = {},
                    placeholder = "Cari Mahasiswa",
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(){
                    items(mahasiswa.size, key = {mahasiswa[it].nim}){
                        MahasiswaItem(mahasiswa = mahasiswa[it],
                            modifier = Modifier
                                .padding(vertical = 4.dp),
                            onDelete = {}
                        )
                    }
                }
            }
        }

    }
}