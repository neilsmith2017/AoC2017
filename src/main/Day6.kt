package main

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class Day6 {

    private val filename = "Day6data.txt"

    val banks = mutableListOf<Int>()

    fun loadData() {
        File(filename).readLines()[0].split("\t".toRegex()).forEach {
            banks.add(it.toInt())
        }
    }


}

class Day6Test {

    private val day6 = Day6()

    @Test
    fun loadDataHasTen() {
        day6.loadData()
        assertEquals(16, day6.banks.size)
    }


}

