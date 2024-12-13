package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay13Test {

    private val solution: SolutionDay = SolutionDay13()

    @Test
    fun partOne() {
        assertEquals(480L, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(875318608908L, solution.partTwo())
    }
}