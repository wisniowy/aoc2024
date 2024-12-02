package com.wisniowy.aoc.days;

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay2Test {

    private val solution: SolutionDay = SolutionDay2()

    @Test
    fun partOne() {
        assertEquals(2, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(4, solution.partTwo())
    }
}