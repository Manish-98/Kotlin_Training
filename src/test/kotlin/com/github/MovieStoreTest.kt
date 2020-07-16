package com.github

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieStoreTest {

    @Test
    fun `test movies released in year 2018 are returned`() {
        val movieStore = MovieStore()
        val movie = Movie("tt0000005", "movie5", LocalDate.of(2018, 3, 29), listOf
        ("Genre 5", "Genre 3"), "Director3", listOf("Actor 2", "Actor 3", "Actor 4"), listOf("Actress 4"),125)
        val year = 2018
        val expectedMovies = listOf(movie)

        movieStore.movieReleasedInYear(year) shouldBe expectedMovies
    }

    @Test
    fun `test movies with duration higher than 140 returned`() {
        val movieStore = MovieStore()
        val movie = Movie("tt0000004", "movie4", LocalDate.of(2020, 2, 29), listOf
        ("Genre 1", "Genre 3"), "Director3", listOf("Actor 2"), listOf("Actress 1"),150)
        val duration = 140
        val expectedMovies = listOf(movie)

        movieStore.movieDurationHigherThan(duration) shouldBe expectedMovies
    }

    @Test
    fun `test movies with Actor 4 returned`() {
        val movieStore = MovieStore()
        val movie = Movie("tt0000005", "movie5", LocalDate.of(2018, 3, 29), listOf
        ("Genre 5", "Genre 3"), "Director3", listOf("Actor 2", "Actor 3", "Actor 4"), listOf("Actress 4"),125)
        val actor = "Actor 4"
        val expectedMovies = listOf(movie)

        movieStore.movieHasActor(actor) shouldBe expectedMovies
    }

    @Test
    fun `test movies with Actress 4 returned`() {
        val movieStore = MovieStore()
        val movie = Movie("tt0000005", "movie5", LocalDate.of(2018, 3, 29), listOf
        ("Genre 5", "Genre 3"), "Director3", listOf("Actor 2", "Actor 3", "Actor 4"), listOf("Actress 4"),125)
        val actress = "Actress 4"
        val expectedMovies = listOf(movie)

        movieStore.movieHasActress(actress) shouldBe expectedMovies
    }
}