package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay
import com.wisniowy.aoc.utils.sctruct.Map2D
import com.wisniowy.aoc.utils.sctruct.Point2D
import com.wisniowy.aoc.utils.sctruct.Map2D.AdjacencyDirections.NORTH
import com.wisniowy.aoc.utils.sctruct.Map2D.AdjacencyDirections.EAST
import com.wisniowy.aoc.utils.sctruct.Map2D.AdjacencyDirections.SOUTH
import com.wisniowy.aoc.utils.sctruct.Map2D.AdjacencyDirections.WEST

/**
 * Advent Of Code 2024 - Day 6
 * @see <a href="https://adventofcode.com/2024/day/6">Advent Of Code 2024 - Day 6</a>
 */
class SolutionDay6 : SolutionDay(6) {

    override fun partOne(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString(FileReader.InputPart.PART_ONE))
        val startPos = map2D.getPoints().entries.first{ it.value == '^' }.key
        return findPath(startPos, map2D).size
    }

    override fun partTwo(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString(FileReader.InputPart.PART_TWO))
        val startPos = map2D.getPoints().entries.first{ it.value == '^' }.key
        return findPath(startPos, map2D).filter { it != startPos }
            .count { visitedPos -> isLoopInPath(startPos, map2D, visitedPos) }
    }

    private fun findPath(startPos: Point2D, map2D: Map2D<Char>): Set<Point2D> {
        val visited = mutableSetOf<Point2D>()

        var pos = startPos
        var direction = Map2D.AdjacencyDirections.NORTH

        while (map2D.getPoint(pos) != null) {
            val nextPos = pos.plus(direction.vector)

            if (map2D.getPoint(nextPos) == '#') {
                direction = getNextDir(direction)
            } else {
                visited.add(pos)
                pos = nextPos
            }
        }

        return visited
    }

    private fun isLoopInPath(startPos: Point2D, map2D: Map2D<Char>, visitedPos: Point2D): Boolean {
        val visited = mutableSetOf<Pair<Point2D, Map2D.AdjacencyDirections>>()
        var pos = startPos
        var dir = NORTH

        while (map2D.getPoint(pos) != null) {
            if ((pos to dir) in visited) {
                return true
            }

            val nextPos = pos.plus(dir.vector)

            if (nextPos == visitedPos || map2D.getPoint(nextPos) == '#') {
                dir = getNextDir(dir)
            } else {
                visited.add(pos to dir)
                pos = nextPos
            }
        }

        return false
    }

    private fun getNextDir(direction: Map2D.AdjacencyDirections): Map2D.AdjacencyDirections {
        return when(direction) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            else -> NORTH
        }
    }
}