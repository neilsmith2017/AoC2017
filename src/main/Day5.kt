package main

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class Day5 {

    fun walkList(instructions : MutableList<Int>) :Int {

        var stepsTaken = 0
        var currentPosition = 0

        while (true) {
            stepsTaken++
            if (currentPosition + instructions[currentPosition] !in 0 until instructions.size) {
                break
            }
            val newPosition = currentPosition + instructions[currentPosition]
            instructions[currentPosition]++
            currentPosition = newPosition
        }

        return stepsTaken
    }

    fun readFileIntoArray(fileName : String) : MutableList<Int> {

        val instructions : MutableList<Int> = arrayListOf()

        File(fileName).forEachLine {
            instructions.add(it.toInt())
        }

        return instructions
    }
}

class Day5Test {

    private val day5 = Day5()

    @Test
    fun checkSimple() {
        val instructions = mutableListOf(0, 3, 0, 1, -3)
        assertEquals(5, day5.walkList(instructions))
    }

    @Test
    fun checkFile() {
        val instructions = day5.readFileIntoArray("Day5data.txt")
        assertEquals(315613, day5.walkList(instructions))
    }
}

class Day5Part2 {

    fun walkList(instructions : MutableList<Int>) :Int {

        var stepsTaken = 0
        var currentPosition = 0

        while (true) {
            stepsTaken++
            if (currentPosition + instructions[currentPosition] !in 0 until instructions.size) {
                break
            }

            val newPosition = currentPosition +  instructions[currentPosition]

            if (instructions[currentPosition] >= 3) {
                instructions[currentPosition] = instructions[currentPosition] - 1
            } else {
                instructions[currentPosition]++
            }
            currentPosition = newPosition
        }

        return stepsTaken
    }

    fun readFileIntoArray(fileName : String) : MutableList<Int> {

        val instructions : MutableList<Int> = arrayListOf()

        File(fileName).forEachLine {
            instructions.add(it.toInt())
        }

        return instructions
    }

}

class Day5Part2Test {

    private val day5Part2 = Day5Part2()

    @Test
    fun checkSimple() {
        val instructions = mutableListOf(0, 3, 0, 1, -3)
        assertEquals(10, day5Part2.walkList(instructions))
        println("$instructions")
    }

    @Test
    fun checkFile() {
        val instructions = day5Part2.readFileIntoArray("Day5data.txt")
        assertEquals(22570529, day5Part2.walkList(instructions))
    }

}