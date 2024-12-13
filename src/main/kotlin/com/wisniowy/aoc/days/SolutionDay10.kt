package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay
import com.wisniowy.aoc.utils.sctruct.Map2D
import com.wisniowy.aoc.utils.sctruct.Point2D

/**
 * Advent Of Code 2024 - Day 10
 * @see <a href="https://adventofcode.com/2024/day/10">Advent Of Code 2024 - Day 10</a>
 */
class SolutionDay10 : SolutionDay(10) {

    override fun partOne(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString(FileReader.InputPart.PART_ONE))
        return map2D.getPoints().entries.filter { it.value.digitToInt() == 0 }
            .sumOf { getTrailScore(it.key, map2D) }
    }

    override fun partTwo(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString(FileReader.InputPart.PART_ONE))
        return map2D.getPoints().entries.filter { it.value.digitToInt() == 0 }
            .sumOf { getTrailScore(it.key, map2D, true) }
    }

    private fun getTrailScore(startPos: Point2D, map2D: Map2D<Char>, partTwo: Boolean = false): Int {
        val q = ArrayDeque<Point2D>()
        q.addLast(startPos)

        val visited = mutableSetOf<Point2D>()

        var score = 0

        while (q.isNotEmpty()) {
            val currentPos = q.removeFirst()

            if (map2D.getPoint(currentPos)!!.digitToInt() == 9) {
                score += 1
            }

            map2D.getAdjacentPoints(currentPos)
                .filter { !visited.contains(it) || partTwo }
                .filter { (map2D.getPoint(it)!!.digitToInt() - map2D.getPoint(currentPos)!!.digitToInt()) == 1 }
                .forEach { nextPos ->
                    visited.add(nextPos)
                    q.addLast(nextPos)
                }
        }
        return score
    }
}