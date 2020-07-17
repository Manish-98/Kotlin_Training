package com.github

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieStoreTest {

    @Test
    fun `test movies released in year 2009 are returned`() {
        val movieStore = MovieStore()
        val movie = Movie(title = "3 Idiots",
                releaseDate = LocalDate.of(2009, 12, 25),
                actors = listOf("Amir Khan", "Sanjay Dutt"),
                actresses = listOf("Kareena Kapoor"),
                genres = listOf(Genre.COMEDY, Genre.DRAMA),
                imdbId = "tt1187043",
                director = "Raju Hirani",
                duration = 170)
        val year = 2009
        val expectedMovies = listOf(movie)

        movieStore.movieReleasedInYear(year) shouldBe expectedMovies
    }

    @Test
    fun `test movies with duration higher than 200 returned`() {
        val movieStore = MovieStore()
        val movie = Movie(title = "Sholay",
                releaseDate = LocalDate.of(1975, 8, 15),
                actors = listOf("Dharmendra", "Amithab"),
                actresses = listOf("Java Bhadhri", "Hema Malini"),
                genres = listOf(Genre.ACTION),
                imdbId = "tt0073707",
                director = "Hema Malini",
                duration = 210)
        val duration = 200
        val expectedMovies = listOf(movie)

        movieStore.movieDurationHigherThan(duration) shouldBe expectedMovies
    }

    @Test
    fun `test movies with Actor Dharmendra returned`() {
        val movieStore = MovieStore()
        val movie = Movie(title = "Sholay",
                releaseDate = LocalDate.of(1975, 8, 15),
                actors = listOf("Dharmendra", "Amithab"),
                actresses = listOf("Java Bhadhri", "Hema Malini"),
                genres = listOf(Genre.ACTION),
                imdbId = "tt0073707",
                director = "Hema Malini",
                duration = 210)
        val actor = "Dharmendra"
        val expectedMovies = listOf(movie)

        movieStore.movieHasActor(actor) shouldBe expectedMovies
    }

    @Test
    fun `test movies with Actress Hema Malini returned`() {
        val movieStore = MovieStore()
        val movie = Movie(title = "Sholay",
                releaseDate = LocalDate.of(1975, 8, 15),
                actors = listOf("Dharmendra", "Amithab"),
                actresses = listOf("Java Bhadhri", "Hema Malini"),
                genres = listOf(Genre.ACTION),
                imdbId = "tt0073707",
                director = "Hema Malini",
                duration = 210)
        val actress = "Hema Malini"
        val expectedMovies = listOf(movie)

        movieStore.movieHasActress(actress) shouldBe expectedMovies
    }

    @Test
    fun `test data read correctly from csv file`() {
        val movieStore = MovieStore()
        val expectedList = listOf(listOf("title","releaseDate","actors","actresses","collectionInCrore","genres","imdbID","director","duration"),
                listOf("3 Idiots","2009/12/25","Amir Khan|Sanjay Dutt","Kareena Kapoor","326","Comedy|Drama","tt1187043","Raju Hirani","170"))

        movieStore.readCSVFile("/Users/manish/IdeaProjects/kotlin-hello-world/src/main/resources/MovieTest.csv") shouldBe expectedList
    }

    @Test
    fun `test conversion of lists into movie objects`() {
        val movieStore = MovieStore()
        val listOfLists = listOf(listOf("title","releaseDate","actors","actresses","collectionInCrore","genres","imdbID","director","duration"),
                listOf("3 Idiots","2009/12/25","Amir Khan|Sanjay Dutt","Kareena Kapoor","326","Comedy|Drama","tt1187043","Raju Hirani","170"))
        val expectedList = listOf(
                Movie(title = "3 Idiots",
                    releaseDate = LocalDate.of(2009, 12, 25),
                    actors = listOf("Amir Khan", "Sanjay Dutt"),
                    actresses = listOf("Kareena Kapoor"),
                    genres = listOf(Genre.COMEDY, Genre.DRAMA),
                    imdbId = "tt1187043",
                    director = "Raju Hirani",
                    duration = 170))

        movieStore.convertToObjectList(listOfLists) shouldBe expectedList
    }
}