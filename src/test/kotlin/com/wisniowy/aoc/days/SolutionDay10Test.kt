package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay10Test {

    private val solution: SolutionDay = SolutionDay10()

    @Test
    fun partOne() {
        assertEquals(36, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(81, solution.partTwo())
    }
}