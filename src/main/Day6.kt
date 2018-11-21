package main

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class Day6 {

    private val filename = "Day6data.txt"
    val banks = arrayListOf<Int>()

    val states = mutableSetOf<String>()

    fun loadData() {
        loadData(filename)
    }

    fun loadData(f: String) {
        File(f).readLines()[0].split("\t".toRegex()).forEach {
            banks.add(it.toInt())
        }
    }

    fun compressBanksToString(): String {
        var result: String = "#"
        banks.forEach {
            result += it
            result += "#"
        }
        return result
    }

    fun getBankWithHighestNumber(): Int {
        val highest = banks.max()
        return banks.indexOfFirst { it == highest }
    }

    fun redistribute(index: Int) {
        val num = banks[index]
        banks[index] = 0

        val numForEach: Int = num / banks.size
        val remainder = num % banks.size

        (0 until banks.size).forEach { banks[it] += numForEach }

        (index + 1..index + remainder).forEach {
            banks[it % banks.size] += 1
        }
    }

    fun process(): Int {
        var steps = 0
        states.add(compressBanksToString())
        println(compressBanksToString())

        while (true) {
            val setSize = states.size
            redistribute(getBankWithHighestNumber())
            steps++
            val state = compressBanksToString()
            println("$state")
            states.add(state)
            if (states.size == setSize) break
        }

        return steps
    }

    fun appearsAgain(loopState: Int): Int {
        var steps = 0
        states.clear()
        return process()
    }

}

class Day6Test {

    @Test
    fun loadDataHasTen() {
        val day6 = Day6()
        day6.loadData()
        assertEquals(16, day6.banks.size)
    }

    @Test
    fun testCompress() {
        val d = Day6()
        d.loadData()
        assertEquals("#0#5#10#0#11#14#13#4#11#8#8#7#1#4#12#11#", d.compressBanksToString())
    }

    @Test
    fun testGetBankWithHighest() {
        val d = Day6()
        d.loadData()
        assertEquals(5, d.getBankWithHighestNumber())
    }

    @Test
    fun testRedist() {
        val d = Day6()
        d.loadData()
        val h = d.getBankWithHighestNumber()
        d.redistribute(h)
        assertEquals("#1#6#11#1#11#0#14#5#12#9#9#8#2#5#13#12#", d.compressBanksToString())
    }

    @Test
    fun testProcess() {
        val d = Day6()
        d.loadData("Day6SmallData.txt")
        assertEquals(5, d.process())
    }


    @Test
    fun testBigData() {
        val d = Day6()
        d.loadData()
        assertEquals(7864, d.process())
    }

    @Test
    fun testGoAgainSmallData() {
        val d = Day6()
        d.loadData("Day6SmallData.txt")
        val loopState = d.process()
        assertEquals(4, d.appearsAgain(loopState))
    }



    @Test
    fun testGoAgainData() {
        val d = Day6()
        d.loadData()
        val loopState = d.process()
        assertEquals(1695, d.appearsAgain(loopState))
    }


}

