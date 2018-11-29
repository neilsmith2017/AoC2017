package main

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

data class Guy(val name: String, val weight: Int, var guyBelow: String?, val guysAbove: MutableSet<String>, var weightAbove: Int = 0)


class Day7 {

    val guys = mutableMapOf<String, Guy>()

    fun loadData(f: String) {
        File(f).readLines().forEach { it ->
            val guy = parseLine(it)
            guys[guy.name] = guy
        }
    }

    fun parseLine(line: String): Guy {
        val words = line.split(" ")
        val potentialGuysAbove = if (words.size > 3) words.subList(3, words.size) else emptyList()
        val guysAbove = mutableSetOf<String>()
        potentialGuysAbove.forEach {
            if (it.endsWith(',')) guysAbove.add(it.substring(0, it.lastIndex)) else guysAbove.add(it)
        }
        val weight = words[1].substring(1 until words[1].lastIndex).toInt()
        return Guy(words[0], weight, null, guysAbove)
    }

    fun sortOutGuysAbove() {
        guys.map { it -> it.key }
                .forEach { nameOfGuy ->
                    val g = guys[nameOfGuy]
                    g?.guysAbove?.forEach { a -> guys[a]?.guyBelow = g.name }
                }
    }

    fun findGuyWithNoOneBelow(): String {
        return guys.filter { it.value.guyBelow == null }.toList().first().second.name
    }

    fun calculateWeightAbove(): Int {


        guys.filter { it -> it.value.guysAbove.isEmpty() }
                .values
                .forEach { topGuy ->
                    val guyBelow = guys[topGuy.guyBelow]
                    if (guyBelow != null) guyBelow.weightAbove += topGuy.weight
                }

        while (true) {
            guys.filter { it -> it.value.weightAbove != 0 }
        }


        return 0
    }

    fun aboveGuyWeight(g: Guy): Int {
        if (!g.guysAbove.isEmpty()) {
            g.guysAbove.forEach { it ->
                val aboveGuy = guys[it]
                if (aboveGuy != null) {
                    g.weightAbove += aboveGuyWeight(aboveGuy)
                }
            }
        }
        return g.weightAbove + g.weight
    }

    fun outOfBalanceGuy(g: Guy): String {
        val grouped = g.guysAbove.map { it ->
            val aboveGuy = guys[it]
            if (aboveGuy != null) {
                Pair(aboveGuy.name, aboveGuy.weightAbove + aboveGuy.weight)
            } else Pair("none", 0)
        }
                .groupBy { it.second }
        if (grouped.size > 1) {
            val chosen =  grouped.filter { it -> it.value.size == 1 }.flatMap{ it -> it.value }
            val newGuy = guys[chosen[0].first]
            if (newGuy != null) return outOfBalanceGuy(newGuy)
//            if (.size == 1)

//                .filter { it -> it.value.size == 1 }
//                .map { it -> it.value.get(0).first }
        } else {
            return g.name
        }
        return ""
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

    @Test
    fun checkSmallData() {
        val day7 = Day7()

        day7.loadData("Day7SmallData.txt")
        day7.sortOutGuysAbove()
        assertEquals("tknk", day7.findGuyWithNoOneBelow())
    }

    @Test
    fun checkBigData() {
        val day7 = Day7()

        day7.loadData("Day7data.txt")
        day7.sortOutGuysAbove()
        assertEquals("vmpywg", day7.findGuyWithNoOneBelow())
    }


    @Test
    fun part2() {
        val day7 = Day7()

        day7.loadData("Day7SmallData.txt")
        day7.sortOutGuysAbove()
        val guy = day7.guys[day7.findGuyWithNoOneBelow()]
        if (guy != null) {
            day7.aboveGuyWeight(guy)
            val oobg = day7.outOfBalanceGuy(guy)
            assertEquals("ugml", oobg)
        }
    }

    @Test
    fun bigPart2() {
        val day7 = Day7()

        day7.loadData("Day7data.txt")
        day7.sortOutGuysAbove()
        val guy = day7.guys[day7.findGuyWithNoOneBelow()]
        if (guy != null) {
            day7.aboveGuyWeight(guy)
            val oobg = day7.outOfBalanceGuy(guy)
            assertEquals("ncrxh", oobg)
        }

        val theGuy = day7.guys["ncrxh"]
        val belowGuy = day7.guys[theGuy?.guyBelow]
        val aboveGuys = belowGuy?.guysAbove?.map { it -> day7.guys[it] }


    }
}