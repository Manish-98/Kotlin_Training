package com.github

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class Genre(genre: String) {
    COMEDY("Comedy"),
    ACTION("Action"),
    DRAMA("Drama");

    companion object {
        fun getListOfGenres(genres: List<String>) : List<Genre> {
            val genreList = mutableListOf<Genre>()
            for (genre in genres) {
                genreList.add(valueOf(genre.toUpperCase()))
            }
            return genreList
        }
    }
}

data class Movie(val imdbId: String, val title: String, val releaseDate: LocalDate, val genres: List<Genre>,
                 val director: String, val actors: List<String>, val actresses: List<String>, val duration: Int) {

    override fun toString(): String {
        return "Movie(imdbId='$imdbId', title='$title', releaseDate=$releaseDate, genres=$genres, director='$director', actors=$actors, actresses=$actresses, duration=$duration)"
    }

    companion object {
        fun toMovie(movieString: List<String>) : Movie {
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            return Movie(
                    title = movieString[0],
                    releaseDate = LocalDate.parse(movieString[1], formatter),
                    actors = movieString[2].split("|"),
                    actresses = movieString[3].split("|"),
                    genres = Genre.getListOfGenres(movieString[5].split("|")),
                    imdbId = movieString[6],
                    director = movieString[7],
                    duration = Integer.parseInt(movieString[8])
            )
        }
    }
}

class MovieStore() {

    val movies: List<Movie>

    init {
        val fileName = "/Users/manish/IdeaProjects/kotlin-hello-world/src/main/resources/movies.csv"
        val movieList = readCSVFile(fileName)
        movies = convertToObjectList(movieList)
    }

    fun readCSVFile(fileName: String): List<List<String>> = csvReader().readAll(File(fileName))

    fun convertToObjectList(movieList: List<List<String>>): List<Movie> {
        val movies = mutableListOf<Movie>()

        for (row in movieList.subList(1, movieList.size)) {
            movies.add(Movie.toMovie(row))
        }
        return movies
    }

    fun movieReleasedInYear(year: Int): List<Movie> = movies.filter {
        it.releaseDate.year == year
    }

    fun movieDurationHigherThan(duration: Int): List<Movie> = movies.filter {
        it.duration > duration
    }

    fun movieHasActor(actor: String): List<Movie> = movies.filter {
        it.actors.contains(actor)
    }

    fun movieHasActress(actress: String): List<Movie> = movies.filter {
        it.actresses.contains(actress)
    }
}