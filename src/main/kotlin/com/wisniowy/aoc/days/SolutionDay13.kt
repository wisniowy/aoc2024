package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay

private const val OFFSET = 10000000000000L

/**
 * Advent Of Code 2024 - Day 13
 * @see <a href="https://adventofcode.com/2024/day/13">Advent Of Code 2024 - Day 13</a>
 */
class SolutionDay13 : SolutionDay(13) {

    data class Machine(val aX: Long, val aY: Long, val bX: Long, val bY: Long, val prizeX: Long, val prizeY: Long)
    override fun partOne(): Any {
        return parseMachines()
            .mapNotNull { getTokenCost(it) }
            .sumOf { it }
    }

    override fun partTwo(): Any {
        return parseMachines()
            .map { with(it) { Machine(aX, aY, bX, bY, prizeX + OFFSET, prizeY + OFFSET) } }
            .mapNotNull { getTokenCost(it) }
            .sumOf { it }
    }

    private fun parseMachines(): List<Machine> {
        val buttonRegex = Regex("Button (A|B): X\\+(?<xVal>[0-9]+), Y\\+(?<yVal>[0-9]+)")
        val prizeRegex = Regex("Prize: X=(?<xVal>[0-9]+), Y=(?<yVal>[0-9]+)")

        return inputAsString().split("\n\n").map { machineStr ->
            val lines = machineStr.lines()
            val (aX, aY) = buttonRegex.matchEntire(lines[0])
                .let { it!!.groups["xVal"]!!.value.toLong() to it.groups["yVal"]!!.value.toLong() }
            val (bX, bY) = buttonRegex.matchEntire(lines[1])
                .let { it!!.groups["xVal"]!!.value.toLong() to it.groups["yVal"]!!.value.toLong() }
            val (prizeX, prizeY) = prizeRegex.matchEntire(lines[2])
                .let { it!!.groups["xVal"]!!.value.toLong() to it.groups["yVal"]!!.value.toLong() }
            Machine(aX, aY, bX, bY, prizeX, prizeY)
        }
    }

    private fun getTokenCost(machine: Machine): Long? {
        return with(machine) {
            val b = (prizeY * aX - prizeX * aY) / (bY * aX - bX * aY)
            val a = (prizeX - b * bX) / aX

            if ((a * aX + b * bX) == prizeX && (a * aY + b * bY) == prizeY) a * 3 + b else null
        }
    }
}