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
