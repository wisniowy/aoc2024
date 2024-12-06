package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay5Test {

    private val solution: SolutionDay = SolutionDay5()

    @Test
    fun partOne() {
        assertEquals(143, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(123, solution.partTwo())
    }
}