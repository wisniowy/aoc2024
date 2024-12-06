package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay6Test {

    private val solution: SolutionDay = SolutionDay6()

    @Test
    fun partOne() {
        assertEquals(41, solution.partOne())
    }

    @Test
    fun partTwo() {
        assertEquals(6, solution.partTwo())
    }
}