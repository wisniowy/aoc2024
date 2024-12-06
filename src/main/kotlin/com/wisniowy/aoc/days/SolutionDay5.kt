package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay

/**
 * Advent Of Code 2024 - Day 5
 * @see <a href="https://adventofcode.com/2024/day/5">Advent Of Code 2024 - Day 5</a>
 */
class SolutionDay5 : SolutionDay(5) {

    override fun partOne(): Any {
        val (rules, updates) = parseInput(inputAsString(FileReader.InputPart.PART_ONE).split("\n\n"))

        return updates.filter { isSorted(it, rules) }
            .sumOf { it[it.size / 2].toInt() }
    }

    private fun parseInput(input: List<String>): Pair<Map<String, List<String>>, List<List<String>>> {
        val rules = input[0].trim().lines()
            .map { line -> line.split('|').let { Pair(it[0], it[1]) } }
            .groupBy ( {it.first}, {it.second})

        val updates = input[1].trim().lines().map { it.split(',') }

        return rules to updates
    }

    private fun isSorted(update: List<String>, rules: Map<String, List<String>>): Boolean {
        return update.mapIndexed { index, page ->
            update.subList(0, index)
                .intersect(rules.getOrDefault(page, emptyList()).toSet())
                .isEmpty()
        }.all { it }
    }

    override fun partTwo(): Any {
        val (rules, updates) = parseInput(inputAsString(FileReader.InputPart.PART_TWO).split("\n\n"))

        val comparator = Comparator { o1: String, o2: String -> when {
                rules.containsKey(o1) && rules[o1]!!.contains(o2) -> -1
                rules.containsKey(o2) && rules[o2]!!.contains(o1) -> 1
                else -> -1
            }
        }

        return updates.filterNot { isSorted(it, rules) }
            .map { it.sortedWith(comparator) }
            .sumOf { it[it.size / 2].toInt() }
    }

}