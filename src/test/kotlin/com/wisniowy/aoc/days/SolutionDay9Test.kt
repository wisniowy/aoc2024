package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay9Test {

    private val solution: SolutionDay = SolutionDay9()

    @Test
    fun partOne() {
        assertEquals(1928, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(2858, solution.partTwo())
    }
}