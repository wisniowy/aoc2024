package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay
import com.wisniowy.aoc.utils.contentEquals
import com.wisniowy.aoc.utils.sctruct.Map2D
import com.wisniowy.aoc.utils.sctruct.Point2D
import com.wisniowy.aoc.utils.sctruct.Map2D.AdjacencyDirections.SOUTH_WEST
import com.wisniowy.aoc.utils.sctruct.Map2D.AdjacencyDirections.SOUTH_EAST
import com.wisniowy.aoc.utils.sctruct.Map2D.AdjacencyDirections.NORTH_WEST
import com.wisniowy.aoc.utils.sctruct.Map2D.AdjacencyDirections.NORTH_EAST

/**
 * Advent Of Code 2024 - Day 4
 * @see <a href="https://adventofcode.com/2024/day/4">Advent Of Code 2024 - Day 4</a>
 */
class SolutionDay4 : SolutionDay(4) {

    override fun partOne(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString(FileReader.InputPart.PART_ONE))

        return map2D.getPoints()
            .filter { (_, value) -> value == 'X' }
            .map { (point, _) ->
                Map2D.ADJACENCY_DIRECTIONS_DIAGONALLY.count { dir ->
                    (1..3).map { i ->
                        map2D.getPoint(point.plus(dir.vector * i)) ?: 'X'
                    }.contentEquals(MAS) }
            }.sum()
    }

    override fun partTwo(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString(FileReader.InputPart.PART_TWO))

        return map2D.getPoints()
            .filter { (_, value) -> value == 'A' }
            .map { (point, _) ->
                val first = listOf(
                    map2D.getPoint(point.plus(SOUTH_WEST.vector)) ?: 'X',
                    map2D.getPoint(Point2D(point.x, point.y)) ?: 'X',
                    map2D.getPoint(point.plus(NORTH_EAST.vector)) ?: 'X')

                val second = listOf(
                    map2D.getPoint(point.plus(NORTH_WEST.vector)) ?: 'X',
                    map2D.getPoint(Point2D(point.x, point.y)) ?: 'X',
                    map2D.getPoint(point.plus(SOUTH_EAST.vector)) ?: 'X')

                (first.contentEquals(MAS) || first.reversed().contentEquals(MAS))
                        && (second.contentEquals(MAS) || second.reversed().contentEquals(MAS))
            }.count { it }
    }

    companion object {
        private val MAS = listOf('M', 'A', 'S')
    }
}