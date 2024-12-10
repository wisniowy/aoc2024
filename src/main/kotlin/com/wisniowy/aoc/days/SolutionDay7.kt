package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.SolutionDay
import com.wisniowy.aoc.utils.toLongList

/**
 * Advent Of Code 2024 - Day 7
 * @see <a href="https://adventofcode.com/2024/day/7">Advent Of Code 2024 - Day 7</a>
 */
class SolutionDay7 : SolutionDay(7) {

    private val addOperator: Operator = { x, y -> x + y }
    private val mulOperator: Operator = { x, y -> x * y }
    private val concatOperator: Operator = { x, y -> (x.toString() + y.toString()).toLong() }

    override fun partOne(): Any {
        val operators = listOf(addOperator, mulOperator)
        return getResult(operators)
    }

    override fun partTwo(): Any {
        val operators = listOf(addOperator, mulOperator, concatOperator)
        return getResult(operators)
    }

    private fun getResult(operators: List<Operator>) = inputAsStringList()
        .map { it.split(":") }
        .map {
            val testValue = it[0].toLong()
            val numbers = it[1].trim().toLongList()
            testEquation(testValue, numbers.first(), numbers.drop(1), operators) to testValue
        }
        .filter { it.first }
        .sumOf { it.second }

    private fun testEquation(testValue: Long, currentValue: Long, numbers: List<Long>, operations: List<Operator> ): Boolean {
        if (numbers.isEmpty() && currentValue == testValue) return true
        if (numbers.isEmpty() && testValue != currentValue) return false
        if (currentValue > testValue) return false

        return operations.fold(false) { acc, op ->
            acc || testEquation(testValue, op.invoke(currentValue, numbers.first()), numbers.drop(1), operations)
        }
    }
}

typealias Operator = (Long, Long) -> Long