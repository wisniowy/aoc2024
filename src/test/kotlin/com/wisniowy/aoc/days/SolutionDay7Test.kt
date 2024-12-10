package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay7Test {

    private val solution: SolutionDay = SolutionDay7()

    @Test
    fun partOne() {
        assertEquals(3749L, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(11387L, solution.partTwo())
    }
}