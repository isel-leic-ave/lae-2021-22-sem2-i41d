package pt.isel

/**
 * Generic and Lazy sequence operations with yield
 */

fun <T,R> Sequence<T>.convert(transform: (T) -> R) = sequence {
    for (item in this@convert) {
        yield(transform(item))
    }
}

fun <T> Sequence<T>.where(predicate: (T) -> Boolean) = sequence {
    for (item in this@where) {
        if(predicate(item))
            yield(item)
    }
}

/**
 * Lazy Iterable concatenation.
 */

fun <T> Iterable<T>.concatLazy(other: Iterable<T>): Iterable<T> {
    return ConcatIterable(this, other)
}

class ConcatIterable<T>(private val source: Iterable<T>, private val other: Iterable<T>) : Iterable<T> {
    override fun iterator(): Iterator<T> {
        return ConcatIterator(source.iterator(), other.iterator())
    }
}

class ConcatIterator<T>(val source: Iterator<T>, val other: Iterator<T>) : Iterator<T> {
    override fun hasNext() = source.hasNext() || other.hasNext()

    override fun next(): T {
        return if (source.hasNext()) source.next() else other.next()
    }
}

fun <T> Sequence<T>.concat(other: Sequence<T>) = sequence {
    for (item in this@concat) {
        yield(item)
    }
    for (item in other) {
        yield(item)
    }
}


/**
 * Merges series of adjacent elements.
 */
fun <T : Any?> Sequence<T>.collapse() = sequence<T> {
    val iter = this@collapse.iterator();
    if(iter.hasNext()) {
        /*
         * Yield the first element
         */
        var prev = iter.next()
        yield(prev)
        /**
         * Traverses the rest of the sequence
         */
        while (iter.hasNext()) {
            val curr = iter.next()
            if(curr != prev) {
                yield(curr)
                prev = curr
            }
        }
    }
}

/**
 * Splits in subsequences of given size.
 */
fun <T> Sequence<T>.window(size: Int) = sequence<Sequence<T>> {
    val iter = this@window.iterator()
    while (iter.hasNext()) {
        yield(sequence {
            for(i in 1..size) {
                if(iter.hasNext()) {
                    yield(iter.next())
                }
            }
        })
    }
}
