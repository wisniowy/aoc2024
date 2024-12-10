package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay

/**
 * Advent Of Code 2024 - Day 9
 * @see <a href="https://adventofcode.com/2024/day/9">Advent Of Code 2024 - Day 9</a>
 */
class SolutionDay9 : SolutionDay(9) {

    override fun partOne(): Any {
        val input = inputAsString(FileReader.InputPart.PART_ONE)

        val numbers = mutableListOf<Char?>()

        var fileCnt = 0


        val v = input.mapIndexed { index, c ->
            when  {
                index % 2 == 0 -> {
                    val fileIdx = fileCnt++
                    List(c.digitToInt()) { fileIdx }
                }
                else -> List(c.digitToInt()) { null }
            }
        }.flatMap { it }


        var left = 0
        var right = v.lastIndex

        var result = 0L

        while (left <= right) {
            val currentValue = v[left]

            when {
                currentValue != null -> {
                    result += (left.toLong() * currentValue)
                    left += 1
                }

                else -> {
                    var rightVal = v[right--]
                    while (rightVal == null) {
                        rightVal = v[right--]
                    }

                    result += (left.toLong() * rightVal)
                    left += 1
                }
            }



        }



        return result
    }


    data class Space(var start: Long, var size: Long, val value: Long?)

    override fun partTwo(): Any {
        val input = inputAsString(FileReader.InputPart.PART_ONE)

//        val usedSpace = mutableListOf<Pair<Long, LongRange>>()
//        val freeSpace = mutableListOf<LongRange>()
//        var isFreeSpace = false
//        var diskIndex = 0L
//        var partIndex = 0L
//        input.indices.map { inputIndex ->
//            val partSize = input[inputIndex].digitToInt()
//
//            if(!isFreeSpace) {
//                usedSpace.add(partIndex to (diskIndex until diskIndex + partSize))
//                partIndex++
//            } else {
//                freeSpace.add(diskIndex until diskIndex + partSize)
//            }
//            diskIndex += partSize
//
//            isFreeSpace = !isFreeSpace
//        }
//        usedSpace.reversed().forEachIndexed { index, used ->
//            val usedSize = used.second.count()
//            val freeSpaceIndex = freeSpace.indexOfFirst { it.count() >= usedSize }
//            if (freeSpaceIndex == -1) return@forEachIndexed
//            if (freeSpaceIndex >= usedSpace.size - 1 - index) return@forEachIndexed
//            val freeSpaceRemoved = freeSpace.removeAt(freeSpaceIndex)
//            freeSpace.add(freeSpaceIndex, freeSpaceRemoved.first() + usedSize .. freeSpaceRemoved.last)
//            usedSpace.removeAt(usedSpace.size - 1 - index)
//            usedSpace.add(usedSpace.size - index, used.first to freeSpaceRemoved.first()..<freeSpaceRemoved.first() + usedSize)
//        }
//        return usedSpace.sumOf { used -> used.second.sumOf { it * used.first } }
        var fileCnt = 0L
        var currentIdx = 0L


        val v = input.mapIndexed { index, c ->
            val idx = currentIdx
            currentIdx += c.digitToInt().toLong()
            when  {
                index % 2 == 0 -> {
                    Space(idx ,c.digitToInt().toLong(), fileCnt++)
                }
                else -> Space(idx, c.digitToInt().toLong(), null)
            }
        }.toMutableList()

        val files = v.filter { it.value != null }
        val emptySpaces = v.filter { it.value == null }


        files.reversed().forEach { file ->

            for (emptySpace in emptySpaces) {
                if (emptySpace.size < file.size) continue


                val newStart = emptySpace.start
                emptySpace.start += file.size
                emptySpace.size -= file.size
                file.start = newStart
                break
            }
        }


        val result = files.sumOf { file ->
            (file.start..<(file.start + file.size)).sumOf { it.toLong() * (file.value ?: 0L).toLong() }
        }

//        var right = v.lastIndex
//
//        while (right >= 0) {
//            val currentSpace = v[right]
//            if (currentSpace.value == null) continue
//
//            var left = 0
//
//            while (left < right) {
//                val newSpace = v[left]
//                if (newSpace.value == null && newSpace.size >= currentSpace.size) {
//
//
//                }
//            }
//
//
//        }

        return result
    }

}