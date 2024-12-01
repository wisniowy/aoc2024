package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import kotlin.math.abs

/**
 * Advent Of Code 2024 - Day 1
 * @see <a href="https://adventofcode.com/2024/day/1">Advent Of Code 2024 - Day 1</a>
 */
class SolutionDay1 : SolutionDay(1) {

    override fun partOne(): Any {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        inputAsStringList().forEach { line ->
            line.split("   ").let {
                left.add(it[0].toInt())
                right.add(it[1].toInt())
            }
        }

        return left.sorted().zip(right.sorted()).sumOf { (l, r) -> abs(l - r) }
    }

    override fun partTwo(): Any {
        val left = mutableListOf<Int>()
        val right = mutableMapOf<Int, Int>()

        inputAsStringList().forEach { line ->
            line.split("   ").let {
                left.add(it[0].toInt())
                right[it[1].toInt()] = right.getOrDefault(it[1].toInt(), 0) + 1
            }
        }

        return left.sumOf { it * right.getOrDefault(it, 0) }
    }

}