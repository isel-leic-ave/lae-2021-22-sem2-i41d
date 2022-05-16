package pt.isel

/**
 * Used on Queries 1 to 4
 */
fun Iterable<String>.parseCsv(delimiter: Char = ','): List<Map<String, String>> {
    val iter = this.iterator()
    val headers = iter.next().split(delimiter)
    return mutableListOf<Map<String, String>>().apply {
        while (iter.hasNext()) {
            val item = mutableMapOf<String, String>()
            iter
                .next()
                .split(delimiter)
                .forEachIndexed { index, value ->
                    val propName = headers[index]
                    item[propName] = value
                }
            this.add(item)
        }
    }
}

/**
 * Used on Queries 5
 */
fun Sequence<String>.parseCsv(delimiter: Char = ','): Sequence<Map<String, String>> {
    return sequence {
        val iter = this@parseCsv.iterator()
        val headers = iter.next().split(delimiter)
        while (iter.hasNext()) {
            val item = mutableMapOf<String, String>()
            iter
                .next()
                .split(delimiter)
                .forEachIndexed { index, value ->
                    val propName = headers[index]
                    item[propName] = value
                }
            yield(item)
        }
    }
}
