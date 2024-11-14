package com.example.moviesapp.model

import android.provider.MediaStore.Images

data class Movie(
    val id : String,
    val title : String,
    val year : String,
    val genre : String,
    val director : String,
    val plot : String ,
    val poster : String,
    val images: List<String>,
    val rating : String
)
