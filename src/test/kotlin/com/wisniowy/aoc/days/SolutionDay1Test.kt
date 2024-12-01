package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay1Test {

    private val solution: SolutionDay = SolutionDay1()

    @Test
    fun partOne() {
        assertEquals(11, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(31, solution.partTwo())
    }
}