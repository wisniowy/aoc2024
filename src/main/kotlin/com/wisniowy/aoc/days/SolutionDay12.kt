package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import com.wisniowy.aoc.utils.sctruct.Map2D
import com.wisniowy.aoc.utils.sctruct.Point2D

/**
 * Advent Of Code 2024 - Day 12
 * @see <a href="https://adventofcode.com/2024/day/12">Advent Of Code 2024 - Day 12</a>
 */
class SolutionDay12 : SolutionDay(12) {

    data class Region(val area: Set<Point2D>, val borders: Map<Map2D.AdjacencyDirections, List<Point2D>>)

    override fun partOne(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString())

        val unvisited = mutableSetOf<Point2D>()
        unvisited.addAll(map2D.getPoints().keys)

        var result = 0

        while (unvisited.isNotEmpty()) {
            val region = getRegion(unvisited.first(), map2D)
            result += region.area.size * region.borders.values.sumOf { it.size }
            unvisited.removeAll(region.area)
        }

        return result
    }


    override fun partTwo(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString())

        val unvisited = mutableSetOf<Point2D>()
        unvisited.addAll(map2D.getPoints().keys)

        var result = 0

        while (unvisited.isNotEmpty()) {
            val region = getRegion(unvisited.first(), map2D)
            result += region.area.size * region.borders.entries.sumOf { numberOfSides(it.value.toList(), it.key) }
            unvisited.removeAll(region.area)
        }

        return result
    }

    private fun getRegion(startPoint: Point2D, map2D: Map2D<Char>): Region {
        val visited = mutableSetOf<Point2D>()
        val q = ArrayDeque<Point2D>()
        q.addLast(startPoint)
        visited.add(startPoint)

        val borders = mapOf(
            Map2D.AdjacencyDirections.NORTH to mutableListOf<Point2D>(),
            Map2D.AdjacencyDirections.EAST to mutableListOf<Point2D>(),
            Map2D.AdjacencyDirections.SOUTH to mutableListOf<Point2D>(),
            Map2D.AdjacencyDirections.WEST to mutableListOf<Point2D>(),
        )

        while (q.isNotEmpty()) {
            val current = q.removeFirst()
            val currentType = map2D.getPoint(current)!!

            Map2D.ADJACENCY_DIRECTIONS.forEach { dir ->
                val nextPoint = current + dir.vector
                val nextType = map2D.getPoint(nextPoint)

                if (nextType == null || currentType != nextType) {
                    borders[dir]?.add(nextPoint)
                    return@forEach
                }

                if (!visited.contains(nextPoint)) {
                    visited.add(nextPoint)
                    q.addLast(nextPoint)
                }
            }
        }

        return Region(visited, borders)
    }

    private fun numberOfSides(border: List<Point2D>, dir: Map2D.AdjacencyDirections): Int {
        return when (dir) {
            Map2D.AdjacencyDirections.NORTH, Map2D.AdjacencyDirections.SOUTH ->
                border.groupBy { it.y }.values.sumOf { numberOfConsecutiveRanges(it.map { it.x }) }

            Map2D.AdjacencyDirections.EAST, Map2D.AdjacencyDirections.WEST ->
                border.groupBy { it.x }.values.sumOf { numberOfConsecutiveRanges(it.map { it.y }) }

            else -> 0
        }
    }

    private fun numberOfConsecutiveRanges(border: List<Int>): Int {
        if (border.isEmpty()) return 0

        var cnt = 1
        var prev = border.minOf { it } - 1
        border.sorted().forEach {
            if (it - prev != 1) {
                cnt += 1
            }
            prev = it
        }

        return cnt
    }
}