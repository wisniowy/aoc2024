package com.wisniowy.aoc

import com.wisniowy.aoc.utils.SolutionDayRunner

fun main(args: Array<String>) {
    val dayNumber = if (args.isEmpty()) null else args[0].toInt()
    SolutionDayRunner.run("com.wisniowy.aoc.days", dayNumber)
}