package pt.isel

import pt.isel.sample.Student
import pt.isel.sample.toStudent

/**
 * Generic and eager sequence operations.
 */

fun <T,R> Iterable<T>.convert(transform: (T) -> R): Iterable<R> {
    val res = mutableListOf<R>()
    for (item in this) {
        res.add(transform(item))
    }
    return res
}

fun <T> Iterable<T>.where(predicate: (T) -> Boolean): Iterable<T> {
    val res = mutableListOf<T>()
    for (item in this) {
        if(predicate(item))
            res.add(item)
    }
    return res
}
