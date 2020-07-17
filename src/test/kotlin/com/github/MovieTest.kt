package com.github

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieTest {

    @Test
    fun `should create a movie object from movie details in a list`() {
        val movieDetails = listOf("3 Idiots","2009/12/25","Amir Khan|Sanjay Dutt","Kareena Kapoor","326","Comedy|Drama","tt1187043","Raju Hirani","170")
        val movie = Movie(title = "3 Idiots",
                releaseDate = LocalDate.of(2009, 12, 25),
                actors = listOf("Amir Khan", "Sanjay Dutt"),
                actresses = listOf("Kareena Kapoor"),
                genres = listOf(Genre.COMEDY, Genre.DRAMA),
                imdbId = "tt1187043",
                director = "Raju Hirani",
                duration = 170)

        Movie.toMovie(movieDetails) shouldBe movie
    }
}