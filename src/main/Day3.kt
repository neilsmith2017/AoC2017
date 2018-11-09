package main

import org.junit.Test
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt
import kotlin.test.assertEquals

class SpiralMemory {

    fun getShortestPath(startPosition: Int): Int {

        val layer = calculateLayer(startPosition-0)
        val maxLayerValue = calcMaxLayerValue(layer)
        val targets = getTargets(layer, maxLayerValue)

        val distanceToTarget = targets
            .map { abs(startPosition - 1 - it) }
            .min() ?: 0

        return layer + distanceToTarget
    }

    private fun getTargets(layer: Int, maxLayerValue: Int): List<Int> {
        return listOf(
            getTarget(layer, maxLayerValue, 1),
            getTarget(layer, maxLayerValue, 3),
            getTarget(layer, maxLayerValue, 5),
            getTarget(layer, maxLayerValue, 7)
        )
    }

    private fun getTarget(l: Int, c: Int, n: Int): Int {
        return c - (n * l)
    }

    private fun calcMaxLayerValue(layer: Int): Int {
        return (4 * layer * layer) + (4 * layer)
    }

}

class SpiralMemoryPartTwo {
    fun getGridSize(startPosition: Int): Int {
        return calculateLayer(startPosition)
    }
}

fun calculateLayer(gridPosition: Int) = ceil((-4 + sqrt(16.0 + (16.0 * (gridPosition - 1)))) / 8).toInt()

class SpiralMemoryTest {

    private val spiralMemory = SpiralMemory()

    @Test
    fun checkLayerCalculation() {
        checkCalcLayer(1, 0)
        checkCalcLayer(2, 1)
        checkCalcLayer(9, 1)
        checkCalcLayer(10, 2)
        checkCalcLayer(23, 2)
        checkCalcLayer(25, 2)
        checkCalcLayer(26, 3)
        checkCalcLayer(49, 3)
        checkCalcLayer(50, 4)
    }

    @Test
    fun check() {
        assertEquals(0, spiralMemory.getShortestPath(1))
        assertEquals(3, spiralMemory.getShortestPath(12))
        assertEquals(2, spiralMemory.getShortestPath(23))
        assertEquals(31, spiralMemory.getShortestPath(1024))
        assertEquals(430, spiralMemory.getShortestPath(312051))
    }

    private fun checkCalcLayer(startPosition: Int, expectedResult: Int) {
        assertEquals(expectedResult, calculateLayer(startPosition - 1))
    }
}


class SpiralMemoryPartTwoTest {

    private val spiralMemory = SpiralMemoryPartTwo()

    @Test
    fun checkGridSize() {
        assertEquals(1, spiralMemory.getGridSize(1))
        assertEquals(2, spiralMemory.getGridSize(2))
        assertEquals(2, spiralMemory.getGridSize(9))
    }

}