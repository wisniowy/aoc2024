package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay12Test {

    private val solution: SolutionDay = SolutionDay12()

    @Test
    fun partOne() {
        assertEquals(1930, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(1206, solution.partTwo())
    }
}