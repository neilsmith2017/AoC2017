package main

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

data class Guy(val name: String, val weight: Int, var guyBelow: String?, val guysAbove: MutableSet<String>)


class Day7 {

    private val filename = "Day7data.txt"

    fun loadData() {
        loadData(filename)
    }

    fun loadData(f: String) {
        File(f).readLines().forEach { it ->
            val guy = parseLine(it)
        }
    }

    fun parseLine(line: String): Guy {
        val words = line.split(" ")
        val potentialGuysAbove = if (words.size > 3) words.subList(3, words.size) else emptyList()
        val guysAbove = mutableSetOf<String>()
        potentialGuysAbove.forEach {
            if (it.endsWith(',')) guysAbove.add(it.substring(0, it.lastIndex)) else guysAbove.add(it)
        }
        val weight = words[1].substring(1 until  words[1].lastIndex).toInt()
        return Guy(words[0], weight, null, guysAbove)
    }
}

class Day7Test {

    @Test
    fun checkParseLine() {
        val day7 = Day7()

        val guy = day7.parseLine("pjkgn (170) -> pstttg, xdkoxrg, cvuvxi")
        assertEquals("pjkgn", guy.name)
        assertEquals(170, guy.weight)
        assertEquals(3, guy.guysAbove.size)
        assertTrue(guy.guysAbove.contains("pstttg"))
        assertTrue(guy.guysAbove.contains("xdkoxrg"))
        assertTrue(guy.guysAbove.contains("cvuvxi"))
    }
}