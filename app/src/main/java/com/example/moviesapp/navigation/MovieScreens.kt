package com.example.moviesapp.navigation

import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.When
import java.lang.IllegalArgumentException

enum class MovieScreens {
    HomeScreen,
    DetailsScreen;
    companion object {
        fun fromRoute(route:String?) : MovieScreens
        = when(route?.substringBefore("/")){
            HomeScreen.name  -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not Recognized")

        }
    }
}