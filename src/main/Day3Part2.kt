package main

import org.junit.Test
import kotlin.math.abs
import kotlin.math.max
import kotlin.test.assertEquals


class SpiralMemoryPartTwo {

    var grid = arrayOf<Array<Int>>()

    init {
        var innerArray = arrayOf<Int>()
        innerArray += 1
        grid += innerArray
    }


    fun populate() {

    }

    fun calcScore(position: Int) =
        getNeighbours(position).sumBy { it -> getValueAtPosition(it.first, it.second) }


    fun getNeighbours(position: Int): List<Pair<Int, Int>> {

        var myNeighbours = mutableListOf<Pair<Int, Int>>()

        var myLayer = calculateLayer(position + 1)
        var myCoords = getCoOrdinatesGivenAPosition(position)

        when {
            myCoords == Pair(myLayer, myLayer) -> addTopRightCorner(myNeighbours, myCoords)
            myCoords == Pair(-myLayer, myLayer) -> addTopLeftCorner(myNeighbours, myCoords)
            myCoords == Pair(-myLayer, -myLayer) -> addBottomLeftCorner(myNeighbours, myCoords)
            myCoords == Pair(myLayer, -myLayer) -> addBottomRightCorner(myNeighbours, myCoords)

            myCoords.first == myLayer - 1 && myCoords.second == myLayer -> pos13(myNeighbours, myCoords)
            myCoords.first == myLayer - 1 && myCoords.second == -myLayer -> pos23(myNeighbours, myCoords)
            myCoords.first == myLayer && myCoords.second == myLayer - 1 -> pos11(myNeighbours, myCoords)
            myCoords.first == myLayer && myCoords.second == -myLayer + 1 -> pos9(myNeighbours, myCoords)
            myCoords.first == -myLayer + 1 && myCoords.second == myLayer -> pos15(myNeighbours, myCoords)
            myCoords.first == -myLayer + 1 && myCoords.second == -myLayer -> pos21(myNeighbours, myCoords)
            myCoords.first == -myLayer && myCoords.second == myLayer - 1 -> pos17(myNeighbours, myCoords)
            myCoords.first == -myLayer && myCoords.second == -myLayer + 1 -> pos19(myNeighbours, myCoords)

            myCoords.first == myLayer -> rightHandSide(myNeighbours, myCoords)
            myCoords.first == -myLayer -> leftHandSide(myNeighbours, myCoords)
            myCoords.second == myLayer -> topSide(myNeighbours, myCoords)
            myCoords.second == -myLayer -> bottomSide(myNeighbours, myCoords)
        }

        return myNeighbours.toList()
    }

    private fun bottomSide(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first - 1, myCoords.second)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second + 1)
    }

    private fun topSide(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first + 1, myCoords.second)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second - 1)
        myNeighbours += Pair(myCoords.first, myCoords.second - 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second - 1)
    }

    private fun leftHandSide(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second - 1)
    }

    private fun rightHandSide(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first, myCoords.second - 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second - 1)
    }

    private fun pos19(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second)
    }

    private fun pos17(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second - 1)
    }

    private fun pos21(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second)
    }

    private fun pos15(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first + 1, myCoords.second)
        myNeighbours += Pair(myCoords.first, myCoords.second - 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second - 1)
    }

    private fun pos9(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first - 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second)
    }

    private fun pos11(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second - 1)
    }

    private fun pos23(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second)
    }

    private fun pos13(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first, myCoords.second - 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second - 1)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second)
    }

    private fun addTopRightCorner(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first - 1, myCoords.second)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second - 1)
        myNeighbours += Pair(myCoords.first, myCoords.second - 1)
    }


    private fun addTopLeftCorner(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first + 1, myCoords.second)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second - 1)
        myNeighbours += Pair(myCoords.first, myCoords.second - 1)
    }


    private fun addBottomLeftCorner(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first + 1, myCoords.second)
        myNeighbours += Pair(myCoords.first + 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
    }


    private fun addBottomRightCorner(myNeighbours: MutableList<Pair<Int, Int>>, myCoords: Pair<Int, Int>) {
        myNeighbours += Pair(myCoords.first - 1, myCoords.second)
        myNeighbours += Pair(myCoords.first - 1, myCoords.second + 1)
        myNeighbours += Pair(myCoords.first, myCoords.second + 1)
    }

    private fun getCoOrdinatesGivenAPosition(position: Int) =
        mapLayerAndPositionToCoOrd(calculateLayer(position + 1), getPositionInLayer(position))

    fun getPositionInLayer(position: Int): Int {
        if (position == 0) return 0
        val layer = calculateLayer(position + 1)
        val m = (layer - 1) * 2 + 1
        return position - (m * m)
    }


    fun getValueAtPosition(x: Int, y: Int): Int {
        if (x < grid.size && y < grid[x].size) {
            return grid[x][y]
        } else {
            throw Exception("coordinates not in grid")
        }
    }

    fun getNumberOfEntriesInLayer(layer: Int): Int {
        return if (layer == 0) 1 else layer * 8
    }

    fun mapLayerAndPositionToCoOrd(layer: Int, position: Int): Pair<Int, Int> {
        val x: Int = when (position) {
            in 0..(layer * 2 - 1), getNumberOfEntriesInLayer(layer) - 1 -> layer
            in (layer * 3 + 1)..(layer * 6 - 1) -> -layer
            in 0..(layer * 4) -> (layer * 3 - 1) - position
            else -> position - (layer * 7 - 1)
        }

        val y = when (position) {
            0 -> if (layer == 0) 0 else position - (layer - 1)
            in (layer * 2 - 1)..(layer * 4 - 1) -> layer
            in (layer * 6 - 1)..(layer * 8 - 1) -> -layer
            in 0..(layer * 2) -> position - (layer - 1)
            else -> (layer * 5 - 1) - position
        }

        return Pair(x, y)
    }

    fun mapCoOrdToLayerAndPosition(x: Int, y: Int): Pair<Int, Int> {
        val layer = getLayer(x, y)
        val position = when {
            y == layer -> if (layer == 0) 0 else abs(-(layer * 3 - 1) + x)
            y == -layer -> (layer * 7 - 1) + x
            x == layer -> layer + y - 1
            else -> abs(-(layer * 5 - 1) + y)
        }
        return Pair(layer, position)
    }

    fun getLayer(x: Int, y: Int) = max(abs(x), abs(y))
}


class SpiralMemoryPartTwoTest {

    private val spiralMemory = SpiralMemoryPartTwo()

    @Test
    fun checkNumberOfEntriesInLayer() {
        assertEquals(1, spiralMemory.getNumberOfEntriesInLayer(0))
        assertEquals(8, spiralMemory.getNumberOfEntriesInLayer(1))
        assertEquals(16, spiralMemory.getNumberOfEntriesInLayer(2))
        assertEquals(24, spiralMemory.getNumberOfEntriesInLayer(3))
    }

    @Test(expected = Exception::class)
    fun checkGridSizeOutOfBoundsThrowsException() {
        spiralMemory.getValueAtPosition(1, 1)
    }

    @Test
    fun checkGridSize() {
        val valueAtPosition = spiralMemory.getValueAtPosition(0, 0)
        assertEquals(1, valueAtPosition)
    }

    @Test
    fun checkMapCoOrdToLayerAndPosition() {
        assertEquals(Pair(0, 0), spiralMemory.mapCoOrdToLayerAndPosition(0, 0))

        assertEquals(Pair(1, 0), spiralMemory.mapCoOrdToLayerAndPosition(1, 0))
        assertEquals(Pair(1, 1), spiralMemory.mapCoOrdToLayerAndPosition(1, 1))
        assertEquals(Pair(1, 2), spiralMemory.mapCoOrdToLayerAndPosition(0, 1))
        assertEquals(Pair(1, 3), spiralMemory.mapCoOrdToLayerAndPosition(-1, 1))
        assertEquals(Pair(1, 4), spiralMemory.mapCoOrdToLayerAndPosition(-1, 0))
        assertEquals(Pair(1, 5), spiralMemory.mapCoOrdToLayerAndPosition(-1, -1))
        assertEquals(Pair(1, 6), spiralMemory.mapCoOrdToLayerAndPosition(0, -1))
        assertEquals(Pair(1, 7), spiralMemory.mapCoOrdToLayerAndPosition(1, -1))

        assertEquals(Pair(2, 0), spiralMemory.mapCoOrdToLayerAndPosition(2, -1))
        assertEquals(Pair(2, 3), spiralMemory.mapCoOrdToLayerAndPosition(2, 2))
        assertEquals(Pair(2, 6), spiralMemory.mapCoOrdToLayerAndPosition(-1, 2))
        assertEquals(Pair(2, 11), spiralMemory.mapCoOrdToLayerAndPosition(-2, -2))

        assertEquals(Pair(3, 0), spiralMemory.mapCoOrdToLayerAndPosition(3, -2))
        assertEquals(Pair(3, 3), spiralMemory.mapCoOrdToLayerAndPosition(3, 1))
        assertEquals(Pair(3, 6), spiralMemory.mapCoOrdToLayerAndPosition(2, 3))
        assertEquals(Pair(3, 11), spiralMemory.mapCoOrdToLayerAndPosition(-3, 3))
        assertEquals(Pair(3, 16), spiralMemory.mapCoOrdToLayerAndPosition(-3, -2))
        assertEquals(Pair(3, 17), spiralMemory.mapCoOrdToLayerAndPosition(-3, -3))
        assertEquals(Pair(3, 18), spiralMemory.mapCoOrdToLayerAndPosition(-2, -3))
        assertEquals(Pair(3, 22), spiralMemory.mapCoOrdToLayerAndPosition(2, -3))
        assertEquals(Pair(3, 23), spiralMemory.mapCoOrdToLayerAndPosition(3, -3))
    }

    @Test
    fun checkMapLayerAndPositionToCoOrd() {
        assertEquals(Pair(0, 0), spiralMemory.mapLayerAndPositionToCoOrd(0, 0))

        assertEquals(Pair(1, 0), spiralMemory.mapLayerAndPositionToCoOrd(1, 0))
        assertEquals(Pair(1, 1), spiralMemory.mapLayerAndPositionToCoOrd(1, 1))
        assertEquals(Pair(0, 1), spiralMemory.mapLayerAndPositionToCoOrd(1, 2))
        assertEquals(Pair(-1, 1), spiralMemory.mapLayerAndPositionToCoOrd(1, 3))
        assertEquals(Pair(-1, 0), spiralMemory.mapLayerAndPositionToCoOrd(1, 4))
        assertEquals(Pair(-1, -1), spiralMemory.mapLayerAndPositionToCoOrd(1, 5))
        assertEquals(Pair(0, -1), spiralMemory.mapLayerAndPositionToCoOrd(1, 6))
        assertEquals(Pair(1, -1), spiralMemory.mapLayerAndPositionToCoOrd(1, 7))

        assertEquals(Pair(2, -1), spiralMemory.mapLayerAndPositionToCoOrd(2, 0))
        assertEquals(Pair(2, 2), spiralMemory.mapLayerAndPositionToCoOrd(2, 3))
        assertEquals(Pair(-1, 2), spiralMemory.mapLayerAndPositionToCoOrd(2, 6))
        assertEquals(Pair(-2, -2), spiralMemory.mapLayerAndPositionToCoOrd(2, 11))

        assertEquals(Pair(3, -2), spiralMemory.mapLayerAndPositionToCoOrd(3, 0))
        assertEquals(Pair(3, 1), spiralMemory.mapLayerAndPositionToCoOrd(3, 3))
        assertEquals(Pair(2, 3), spiralMemory.mapLayerAndPositionToCoOrd(3, 6))
        assertEquals(Pair(-3, 3), spiralMemory.mapLayerAndPositionToCoOrd(3, 11))
        assertEquals(Pair(-3, -2), spiralMemory.mapLayerAndPositionToCoOrd(3, 16))
        assertEquals(Pair(-3, -3), spiralMemory.mapLayerAndPositionToCoOrd(3, 17))
        assertEquals(Pair(-2, -3), spiralMemory.mapLayerAndPositionToCoOrd(3, 18))
        assertEquals(Pair(2, -3), spiralMemory.mapLayerAndPositionToCoOrd(3, 22))
        assertEquals(Pair(3, -3), spiralMemory.mapLayerAndPositionToCoOrd(3, 23))
    }

    @Test
    fun checkGetPositionInLayer() {
        assertEquals(0, spiralMemory.getPositionInLayer(0))

        assertEquals(0, spiralMemory.getPositionInLayer(1))
        assertEquals(1, spiralMemory.getPositionInLayer(2))

        assertEquals(0, spiralMemory.getPositionInLayer(9))
        assertEquals(1, spiralMemory.getPositionInLayer(10))
        assertEquals(11, spiralMemory.getPositionInLayer(20))

        assertEquals(0, spiralMemory.getPositionInLayer(25))
    }

    @Test
    fun checkGetLayer() {
        assertEquals(0, spiralMemory.getLayer(0, 0))
        assertEquals(1, spiralMemory.getLayer(1, -1))
        assertEquals(2, spiralMemory.getLayer(2, -1))
        assertEquals(3, spiralMemory.getLayer(0, 3))
        assertEquals(2, spiralMemory.getLayer(-2, 1))

    }
}