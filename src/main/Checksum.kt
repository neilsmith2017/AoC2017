package main

class Checksum {

    fun calculateChecksum(data: String): Int {

        var count = 0

        val rows = splitRows(data)
        for (row in rows) {
            val intElements = splitRow(row)
            val max = intElements.max()
            val min = intElements.min()
            if (min != null && max != null) {
                count += max - min
            }
        }

        return count
    }

    fun calculateEvenlyDivisible(data: String): Int {

        var count = 0

        val rows = splitRows(data)
        for (row in rows) {
            val intElements = splitRow(row)
            count += evenlyDivisibleResult(intElements)
        }

        return count

    }


    private fun splitRows(rows: String): List<String> = rows.trim('\n').split("\n".toRegex())

    private fun splitRow(row: String): List<Int> {
        val stringElements = row.trim().split("\t".toRegex())
        if (stringElements.isNotEmpty()) {
            return stringElements.map {
                if (it.isNotEmpty()) it.toInt() else 0
            }
        }
        return listOf(0)
    }

    fun evenlyDivisibleResult(elements: List<Int>): Int {

        val sortedList = elements.sortedByDescending { it }

        for (i in 0 until sortedList.size - 1) {
            for (j in i + 1 until sortedList.size) {
                if (sortedList[i].rem(sortedList[j]) == 0) {
                    return sortedList[i] / sortedList[j]
                }
            }
        }

        return 0
    }

}