package com.github

import java.time.LocalDate

data class Movie(val imdbId: String, val title: String, val releaseDate: LocalDate, val genres: List<String>,
            val director: String, val actors: List<String>, val actresses: List<String>, val duration: Int){

    override fun toString(): String {
        return "Movie(imdbId='$imdbId', title='$title', releaseDate=$releaseDate, genres=$genres, director='$director', actors=$actors, actresses=$actresses, duration=$duration)"
    }
}

class MovieStore() {

    var movieOne = Movie("tt0000001", "movie1", LocalDate.of(2020, 1, 20) , listOf
    ("Genre 1", "Genre 2"), "Director1", listOf("Actor 1"), listOf("Actress 1"),90)

    var movieTwo = Movie("tt0000002", "movie2", LocalDate.of(2020, 3, 29), listOf
    ("Genre 1", "Genre 3"), "Director2", listOf("Actor 2", "Actor 3"), listOf("Actress 2"),120)

    var movieThree = Movie("tt0000003", "movie3", LocalDate.of(2019, 3, 9), listOf
    ("Genre 4"), "Director1", listOf("Actor 1", "Actor 3"), listOf("Actress 1", "Actress 3"),115)

    var movieFour = Movie("tt0000004", "movie4", LocalDate.of(2020, 2, 29), listOf
    ("Genre 1", "Genre 3"), "Director3", listOf("Actor 2"), listOf("Actress 1"),150)

    var movieFive = Movie("tt0000005", "movie5", LocalDate.of(2018, 3, 29), listOf
    ("Genre 5", "Genre 3"), "Director3", listOf("Actor 2", "Actor 3", "Actor 4"), listOf("Actress 4"),125)

    var movies: List<Movie> = listOf(movieOne, movieTwo, movieThree, movieFour, movieFive)

    fun movieReleasedInYear(year: Int) : List<Movie> = movies.filter {
            it.releaseDate.year == year
        }

    fun movieDurationHigherThan(duration: Int) : List<Movie> = movies.filter {
            it.duration > duration
        }

    fun movieHasActor(actor: String) : List<Movie> = movies.filter {
            it.actors.contains(actor)
        }

    fun movieHasActress(actress: String) : List<Movie> = movies.filter {
            it.actresses.contains(actress)
        }
}

fun main() {
    val movieStore = MovieStore()
    println(movieStore.movieDurationHigherThan(100))
    println(movieStore.movieHasActor("Actor 1"))
    println(movieStore.movieHasActress("Actress 1"))
    println(movieStore.movieReleasedInYear(2020))
}