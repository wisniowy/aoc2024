package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay4Test {

    private val solution: SolutionDay = SolutionDay4()

    @Test
    fun partOne() {
        assertEquals(18, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(9, solution.partTwo())
    }
}