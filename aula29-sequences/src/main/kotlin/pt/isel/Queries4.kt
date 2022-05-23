package pt.isel

/**
 * Generic and Lazy sequence operations.
 */

fun <T,R> Iterable<T>.convertLazy(transform: (T) -> R): Iterable<R> {
    return TransformingIterable(this, transform)
}

class TransformingIterable<T, R>(val source: Iterable<T>, val transform: (T) -> R) : Iterable<R> {
    override fun iterator(): Iterator<R> {
        return TransformingIterator(source.iterator(), transform)
    }
}

class TransformingIterator<T, R>(val iter: Iterator<T>, val transform: (T) -> R) : Iterator<R> {
    override fun hasNext() = iter.hasNext()

    override fun next() = transform(iter.next())
}

fun <T> Iterable<T>.whereLazy(predicate: (T) -> Boolean) = object : Iterable<T> {
    override fun iterator() = object : Iterator<T> {
        val iter = this@whereLazy.iterator()
        /**
         * Do not use this code in real life!!!!
         * The sequence finish when curr is null.s
         */
        var curr: T? = advance()

        private fun advance(): T? {
            while(iter.hasNext()) {
                val item = iter.next()
                if(predicate(item))
                    return item
            }
            return null
        }

        override fun hasNext() = curr != null

        override fun next() : T {
            val item = curr ?: throw Exception("Out of bounds!")
            curr = advance()
            return item
        }
    }
}
