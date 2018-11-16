package main

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day4 {

    fun hasNoDuplicates(s: String) =
        s.split(" ")
            .groupBy { it }
            .filter { it.value.size > 1 }.isEmpty()

    fun multiLines(lines: String) : Int {
        var linesWithoutDuplicates = 0
        lines.split("\n".toRegex()).forEach {
            if (hasNoDuplicates(it)) {
                linesWithoutDuplicates++
            }
        }
        return linesWithoutDuplicates
    }

    fun multiLinesFromFile(fileName: String): Int {
        var linesWithoutDuplicates = 0
        File(fileName).forEachLine {
            if (hasNoDuplicates(it)) {
                linesWithoutDuplicates++
            }
        }
        return linesWithoutDuplicates
    }

    companion object {
        const val smallDataNoDups = "pphsv ojtou brvhsj cer ntfhlra udeh ccgtyzc zoyzmh jum lugbnk\n" +
                "vxjnf fzqitnj uyfck blnl impo kxoow nngd worcm bdesehw\n" +
                "caibh nfuk kfnu llfdbz uxjty yxjut jcea"
        const val smallDataWithDups = "pphsv ojtou brvhsj cer ntfhlra udeh ccgtyzc zoyzmh jum lugbnk\n" +
                "vxjnf fzqitnj uyfck blnl impo kxoow nngd impo bdesehw\n" +
                "caibh nfuk kfnu llfdbz uxjty uxjty jcea"
    }
}
class Day4Part2 {

    fun hasNoDuplicates(s: String) =
        s.split(" ")
            .map { it -> it.toCharArray().sorted() }
            .groupBy { it }
            .filter { it.value.size > 1 }.isEmpty()

    fun multiLines(lines: String) : Int {
        var linesWithoutDuplicates = 0
        lines.split("\n".toRegex()).forEach {
            if (hasNoDuplicates(it)) {
                linesWithoutDuplicates++
            }
        }
        return linesWithoutDuplicates
    }

    fun multiLinesFromFile(fileName: String): Int {
        var linesWithoutDuplicates = 0
        File(fileName).forEachLine {
            if (hasNoDuplicates(it)) {
                linesWithoutDuplicates++
            }
        }
        return linesWithoutDuplicates
    }

    companion object {
        const val smallDataNoDups = "pphsv ojtou brvhsj cer ntfhlra udeh ccgtyzc zoyzmh jum lugbnk\n" +
                "vxjnf fzqitnj uyfck blnl impo kxoow nngd worcm bdesehw\n" +
                "caibh nfuk kfnu llfdbz uxjty yxjut jcea"
        const val smallDataWithDups = "pphsv ojtou brvhsj cer ntfhlra udeh ccgtyzc zoyzmh jum lugbnk\n" +
                "vxjnf fzqitnj uyfck blnl impo kxoow nngd impo bdesehw\n" +
                "caibh nfuk kfnu llfdbz uxjty uxjty jcea"
    }
}


class TestDay4 {

    private val day4 = Day4()

    @Test
    fun checkOneLineWithDuplicates() {
        assertFalse(day4.hasNoDuplicates("aa bb aa"))
    }

    @Test
    fun checkOneLineWithoutDuplicates() {
        assertTrue(day4.hasNoDuplicates("aa bb cc"))
    }

    @Test
    fun checkSmallDataNoDups() {
        assertEquals(3, day4.multiLines(Day4.smallDataNoDups))
    }

    @Test
    fun checkSmallDataOneDup() {
        assertEquals(1, day4.multiLines(Day4.smallDataWithDups))
    }

    @Test
    fun checkBigData() {
        assertEquals(466, day4.multiLinesFromFile("BigData.txt"))
    }
}


class TestDay4Part2 {

    private val day4 = Day4Part2()

    @Test
    fun checkOneLineWithDuplicates() {
        assertFalse(day4.hasNoDuplicates("aa bb aa"))
    }

    @Test
    fun checkOneLineWithoutDuplicates() {
        assertTrue(day4.hasNoDuplicates("aa bb cc"))
    }

    @Test
    fun checkSmallDataNoDups() {
        assertEquals(2, day4.multiLines(Day4.smallDataNoDups))
    }

    @Test
    fun checkSmallDataOneDup() {
        assertEquals(1, day4.multiLines(Day4.smallDataWithDups))
    }

    @Test
    fun checkBigData() {
        assertEquals(251, day4.multiLinesFromFile("BigData.txt"))
    }
}


