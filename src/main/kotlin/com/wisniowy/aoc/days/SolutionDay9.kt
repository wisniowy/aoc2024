package com.wisniowy.aoc.days

import com.wisniowy.aoc.utils.FileReader
import com.wisniowy.aoc.utils.SolutionDay

/**
 * Advent Of Code 2024 - Day 9
 * @see <a href="https://adventofcode.com/2024/day/9">Advent Of Code 2024 - Day 9</a>
 */
class SolutionDay9 : SolutionDay(9) {

    data class Space(var range: LongRange, val value: Long?)

    override fun partOne(): Any {
        val input = inputAsString(FileReader.InputPart.PART_ONE)

        var fileIdx = 0L
        val disk = mutableListOf<Long?>()

        input.indices.forEach { idx ->
            val spaceSize = input[idx].digitToInt()

            if (idx % 2 == 0) {
                repeat(spaceSize) { disk.add(fileIdx) }
                fileIdx += 1
            } else {
                repeat(spaceSize) { disk.add(null) }
            }
        }

        var emptyIdx = disk.indexOfFirst { it == null }

        disk.indices.reversed().forEach { idx ->
            if (disk[idx] == null) return@forEach
            emptyIdx = (emptyIdx..<idx).firstOrNull { disk[it] == null } ?: return@forEach
            disk[emptyIdx] = disk[idx]
            disk[idx] = null
        }

        return disk.zip(disk.indices)
            .filter { it.first != null }
            .sumOf { (it.first ?: 0) * it.second }
    }

    override fun partTwo(): Any {
        val input = inputAsString(FileReader.InputPart.PART_TWO)

        var fileIdx = 0L
        var diskIdx = 0L

        val files = mutableListOf<Space>()
        val freeSpaces = mutableListOf<Space>()

        input.indices.forEach { idx ->
            val spaceSize = input[idx].digitToInt()

            if (idx % 2 == 0) {
                files.add(Space((diskIdx until diskIdx + spaceSize), fileIdx))
                fileIdx += 1
            } else {
                freeSpaces.add(Space((diskIdx until diskIdx + spaceSize), null))
            }

            diskIdx += spaceSize
        }

        files.reversed().forEach { file ->
            val fileSize = file.range.count()
            val firstFreeSpace = freeSpaces.indexOfFirst { it.range.count() >= fileSize }

            if (firstFreeSpace < 0) return@forEach

            val freeSpace = freeSpaces[firstFreeSpace]
            if (freeSpace.range.first >= file.range.first) return@forEach
            file.range = (freeSpace.range.first until freeSpace.range.first + fileSize)
            freeSpace.range = (freeSpace.range.first + fileSize until freeSpace.range.first + freeSpace.range.count())
        }

        return files.sumOf { file -> file.range.sumOf { it * file.value!! } }
    }
}