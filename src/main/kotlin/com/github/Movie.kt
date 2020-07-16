package com.github

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(val imdbId: String, val title: String, val releaseDate: LocalDate, val genres: List<String>,
            val director: String, val actors: List<String>, val actresses: List<String>, val duration: Int){

    override fun toString(): String {
        return "Movie(imdbId='$imdbId', title='$title', releaseDate=$releaseDate, genres=$genres, director='$director', actors=$actors, actresses=$actresses, duration=$duration)"
    }
}

class MovieStore() {

    val movies: List<Movie>

    init {
        val fileName = "/Users/manish/IdeaProjects/kotlin-hello-world/src/main/resources/movies.csv"
        val movieList = readCSVFile(fileName)
        movies = convertToObjectList(movieList)
    }

    fun readCSVFile(fileName: String) : List<List<String>> = csvReader().readAll(File(fileName))

    fun convertToObjectList(movieList: List<List<String>>) : List<Movie> {
        val movies = mutableListOf<Movie>()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

        for (row in movieList.subList(1,movieList.size)) {
            movies.add( Movie (
                    title = row[0],
                    releaseDate = LocalDate.parse(row[1], formatter),
                    actors = row[2].split("|"),
                    actresses = row[3].split("|"),
                    genres = row[5].split("|"),
                    imdbId = row[6],
                    director = row[7],
                    duration = Integer.parseInt(row[8])
            ))
        }

        return movies
    }

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