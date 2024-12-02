package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import com.wisniowy.aoc.utils.toIntList
import kotlin.math.abs

/**
 * Advent Of Code 2024 - Day 2
 * @see <a href="https://adventofcode.com/2024/day/2">Advent Of Code 2024 - Day 2</a>
 */
class SolutionDay2 : SolutionDay(2) {

    override fun partOne(): Any {
        val isSafe: ((List<Int>) -> Boolean) = {
            val diffs = it.zipWithNext().map { (first, second) -> first - second }

            diffs.all { diff -> abs(diff) in (1..3) && diff < 0 }
                    || diffs.all { diff -> abs(diff) in (1..3) && diff > 0 }
        }

        return inputAsStringList().count { isSafe(it.toIntList()) }
    }

    override fun partTwo(): Any {
        val isSafe: ((List<Int>) -> Boolean) = {
            it.indices.any { index ->
                val diffs = it.toMutableList().apply { removeAt(index) }
                    .zipWithNext()
                    .map { (first, second) -> first - second }

                diffs.all { diff -> abs(diff) in (1..3) && diff < 0 }
                        || diffs.all { diff -> abs(diff) in (1..3) && diff > 0 }
            }

        }

        return inputAsStringList().count { isSafe(it.toIntList()) }
    }
}
