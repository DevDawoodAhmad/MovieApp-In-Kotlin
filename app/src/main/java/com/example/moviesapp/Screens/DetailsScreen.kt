package com.example.moviesapp.Screens




import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.data.DataSource
import com.example.moviesapp.data.DataSource.moviesList
import com.example.moviesapp.model.Movie
import com.example.moviesapp.moviesLayout.LayoutOfMovies
import okhttp3.internal.filterList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController,
                  movieId : String?) {
    val newMovieList = moviesList.filter { movie->
       movie.id == movieId
    }

     Scaffold (topBar = {
         TopAppBar(title = {
             Row (horizontalArrangement = Arrangement.Start,
                 verticalAlignment = Alignment.CenterVertically){
                 Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                     modifier = Modifier.clickable { navController.popBackStack() },
                     tint = Color.Blue)
                 Spacer(modifier = Modifier.width(25.dp))

                 Text(text = "Movies" ,
                     color = Color.Blue,
                     style = MaterialTheme.typography.displaySmall)
             }
         },
             colors = TopAppBarDefaults.topAppBarColors(Color.Transparent))
     }) {
         Surface(modifier = Modifier
             .padding(it)
             .fillMaxSize()) {

             Column(
                 verticalArrangement = Arrangement.Top,
                 horizontalAlignment = Alignment.CenterHorizontally
             ) {
                 Text(
                     text = newMovieList.first().title,
                     modifier = Modifier.padding(bottom = 8.dp), fontSize = 20.sp,
                     fontWeight = FontWeight.Bold
                 )
                 LayoutOfMovies(movie = newMovieList.first())

                 Spacer(modifier = Modifier.padding(8.dp))
                 Divider(color = Color.Black,
                     thickness = 2.dp)
                 Text(text = "Movie Images",
                     color = Color.Blue,
                     style = MaterialTheme.typography.bodyLarge,
                     modifier = Modifier.padding(top = 8.dp,
                         bottom = 8.dp))

                 LazyRow(content = MovieImages(newMovieList))


             }
         }

//            Button(
//                onClick = {
//                // --->for going to homeScreen back but it remain in backStack
//                    //navController.navigate(MovieScreens.HomeScreen.name)
//
//                // ---> For moving back but it not remain in back stack
//                   // navController.popBackStack()
//
//                //---> For removing it from up but can navigate by clicking
//                    navController.navigate(MovieScreens.HomeScreen.name){
//                        popUpTo(MovieScreens.HomeScreen.name){
//                            inclusive = true
//                        }
//                    }
//                }
//            ) {
//                Text(text = "Back")
//
//            }

        }
}

@Composable
private fun MovieImages(newMovieList: List<Movie>): LazyListScope.() -> Unit =
    {
        items(newMovieList.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .width(400.dp)
                    .height(240.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }

        }
    }