package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay11Test {

    private val solution: SolutionDay = SolutionDay11()

    @Test
    fun partOne() {
        assertEquals(55312L, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(65601038650482L, solution.partTwo())
    }
}