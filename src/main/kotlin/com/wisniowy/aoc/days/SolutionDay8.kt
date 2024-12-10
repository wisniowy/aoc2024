package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay
import com.wisniowy.aoc.utils.sctruct.Map2D
import com.wisniowy.aoc.utils.sctruct.Point2D
import com.wisniowy.aoc.utils.sctruct.Vector2D

/**
 * Advent Of Code 2024 - Day 8
 * @see <a href="https://adventofcode.com/2024/day/8">Advent Of Code 2024 - Day 8</a>
 */
class SolutionDay8 : SolutionDay(8) {

    override fun partOne(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString(FileReader.InputPart.PART_ONE))

        val groupedAntennas = map2D.getPoints().entries
            .filter { it.value != '.' }
            .groupBy({ it.value }, { it.key })

        return groupedAntennas.values
            .flatMap { it.cartesianProduct(it)
                .filter { pair -> pair.first != pair.second }
                .flatMap { pair -> getAntiNodes(pair.first, pair.second) } }
            .filter { antiNodePos -> map2D.getPoint(antiNodePos) != null }
            .distinct()
            .size
    }

    override fun partTwo(): Any {
        val map2D = Map2D.fromString<Char>(inputAsString(FileReader.InputPart.PART_TWO))

        val groupedAntennas = map2D.getPoints().entries
            .filter { it.value != '.' }
            .groupBy({ it.value }, { it.key })

        val antennaPair = groupedAntennas.values
            .flatMap { it.cartesianProduct(it) }
            .filter { pair -> pair.first != pair.second }

        val anitnodes = antennaPair.flatMap {
                pair -> getAllAntinodes(pair.first, pair.second, map2D) }

        return (antennaPair.flatMap { listOf(it.first, it.second) } + anitnodes).distinct().size
    }

    private fun <A, B> Collection<A>.cartesianProduct(other: Collection<B>): Collection<Pair<A, B>> {
        return this.flatMap { a -> other.map { b ->  a to b} }
    }

    private fun getAntiNodes(a1Pos: Point2D, a2Pos: Point2D): List<Point2D> {
        val vector21 = Vector2D(a1Pos.x - a2Pos.x, a1Pos.y - a2Pos.y)
        val vector12 = Vector2D(a2Pos.x - a1Pos.x, a2Pos.y - a1Pos.y)

        return listOf(
            a1Pos.plus(vector12 * 2),
            a2Pos.plus(vector21 * 2)
        )
    }

    private fun antinodeSequence(pos: Point2D, vector: Vector2D) = sequence {
        var antiNodePos = pos.plus(vector * 2)
        while (true) {
            yield(antiNodePos)
            antiNodePos = antiNodePos.plus(vector)
        }
    }

    private fun getAllAntinodes(a1Pos: Point2D, a2Pos: Point2D, map2D: Map2D<Char>): List<Point2D> {
        val vector21 = Vector2D(a1Pos.x - a2Pos.x, a1Pos.y - a2Pos.y)
        val vector12 = Vector2D(a2Pos.x - a1Pos.x, a2Pos.y - a1Pos.y)

        return antinodeSequence(a1Pos, vector12).takeWhile { map2D.getPoint(it) != null }.toList() +
                antinodeSequence(a2Pos, vector21).takeWhile { map2D.getPoint(it) != null }.toList()
    }
}