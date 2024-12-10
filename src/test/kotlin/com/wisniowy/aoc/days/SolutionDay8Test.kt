package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay8Test {

    private val solution: SolutionDay = SolutionDay8()

    @Test
    fun partOne() {
        assertEquals(14, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(34, solution.partTwo())
    }
}