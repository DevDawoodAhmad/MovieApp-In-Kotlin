package com.example.moviesapp.moviesLayout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.model.Movie

@Composable
fun LayoutOfMovies(
    modifier: Modifier = Modifier,
    movie : Movie,
    onMovieClick :(String) -> Unit = {}){

    var expanded by remember{
        mutableStateOf(false)
    }
    Card (modifier = modifier
        .fillMaxWidth()
        .clickable { onMovieClick(movie.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start){

            Surface (modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RoundedCornerShape(corner = CornerSize(12.dp))
            ) {
//                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null,
//                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RoundedCornerShape(CornerSize(12.dp))))
            }
            Column {

                Text(text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Blue)
                Text(text = "Director : ${movie.director} ",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Light
                )
                Text(text = "Released : ${movie.year}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Light
                )
                AnimatedVisibility(visible = expanded,
                ) {

                    Column {

                        Text( buildAnnotatedString {
                            withStyle( style = SpanStyle(color = Color.DarkGray
                                , fontWeight =  FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            ){
                                append("Plot: ")
                            }

                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp)
                            ){
                                append(movie.plot)
                            }
                        } ,
                            modifier = Modifier.padding(bottom = 6.dp))

                        Divider(color = Color.Blue)
                        Text( buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 16.sp,
                            )
                            ){
                                append(movie.genre)
                            }
                        } , modifier = Modifier.padding(top = 6.dp))
                        Text(text = movie.director)
                        Text(text = "Rating: ${movie.rating}")


                    }
                }

                Icon(imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                    contentDescription = "ArrowDropDown",
                    modifier = Modifier.clickable { expanded = !expanded },
                    tint = Color.Blue)
            }
        }

    }
}