package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay
import com.wisniowy.aoc.utils.toLongList

/**
 * Advent Of Code 2024 - Day 11
 * @see <a href="https://adventofcode.com/2024/day/11">Advent Of Code 2024 - Day 11</a>
 */
class SolutionDay11 : SolutionDay(11) {

    private val cache: MutableMap<Pair<Long, Int>, Long> = mutableMapOf()

    override fun partOne(): Any {
        val initialStones = inputAsString(FileReader.InputPart.PART_ONE).toLongList()
        return initialStones.sumOf { countStones(it, 25) }
    }

    override fun partTwo(): Any {
        val initialStones = inputAsString(FileReader.InputPart.PART_TWO).toLongList()
        return initialStones.sumOf { countStones(it, 75) }
    }

    private fun countStones(stone: Long, depth: Int): Long {
        if (depth == 0) return 1L
        if (cache.containsKey(stone to depth)) return cache[stone to depth]!!

        val cnt = when {
            stone == 0L -> countStones(1L, depth - 1)
            stone.toString().length % 2 == 0 -> {
                val s = stone.toString()
                countStones(s.substring(0, s.length / 2).toLong(), depth - 1) +
                        countStones(s.substring(s.length / 2, s.length).toLong(), depth - 1)
            }
            else -> countStones(stone * 2024L, depth - 1)
        }

        cache[stone to depth] = cnt
        return cnt
    }
}