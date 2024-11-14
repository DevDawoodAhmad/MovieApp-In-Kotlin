package com.example.moviesapp.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.data.DataSource
import com.example.moviesapp.model.Movie
import com.example.moviesapp.moviesLayout.LayoutOfMovies
import com.example.moviesapp.navigation.MovieScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    Scaffold (topBar = {
        TopAppBar(title = {
            Text(text = "Movies",
                style = MaterialTheme.typography.displaySmall,
                color = Color.Blue)
        },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent))
    }){
        ContentList(
            navController= navController,
            movies = DataSource.moviesList,
            modifier = Modifier.padding(it))
    }

}

@Composable
fun ContentList(
    navController: NavController,
    movies : List<Movie>,
                modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier){
        items(movies){
            LayoutOfMovies(movie = it,
                modifier = Modifier.padding(8.dp)){movie->
                //Log.d("OnClick","Main Content $movie")
                navController.navigate(route = MovieScreens.DetailsScreen.name +"/$movie")

            }

        }
    }

}
