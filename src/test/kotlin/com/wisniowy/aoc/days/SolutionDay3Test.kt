package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay3Test {

    private val solution: SolutionDay = SolutionDay3()

    @Test
    fun partOne() {
        assertEquals(161, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(48, solution.partTwo())
    }
}