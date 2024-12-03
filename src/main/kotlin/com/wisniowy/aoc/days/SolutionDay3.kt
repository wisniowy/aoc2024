package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay

/**
 * Advent Of Code 2024 - Day 3
 * @see <a href="https://adventofcode.com/2024/day/3">Advent Of Code 2024 - Day 3</a>
 */
class SolutionDay3 : SolutionDay(3) {

    override fun partOne(): Any {
        val regex = Regex("mul\\((?<first>[0-9]{1,3}),(?<second>[0-9]{1,3})\\)")
        return regex.findAll(inputAsString(FileReader.InputPart.PART_ONE)).sumOf { match -> mulResult(match) }
    }

    override fun partTwo(): Any {
        val regex = Regex("(mul\\((?<first>[0-9]{1,3}),(?<second>[0-9]{1,3})\\))|(do\\(\\))|(don't\\(\\))")

        return regex.findAll(inputAsString(FileReader.InputPart.PART_TWO)).fold(Pair(true, 0)) { acc, match ->
            when {
                match.value.startsWith("do(") -> Pair(true, acc.second)
                match.value.startsWith("don't(") -> Pair(false, acc.second)
                match.value.startsWith("mul(") && acc.first -> Pair(true, acc.second + mulResult(match))
                else -> acc
            }
        }.second
    }

    private fun mulResult(match: MatchResult) =
        match.groups["first"]!!.value.toInt() * match.groups["second"]!!.value.toInt()

}