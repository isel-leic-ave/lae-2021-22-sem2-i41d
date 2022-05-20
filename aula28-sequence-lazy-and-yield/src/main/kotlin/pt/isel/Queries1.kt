package pt.isel

import pt.isel.sample.Student
import pt.isel.sample.toStudent

/**
 * Naif, ad-hoc and eager sequence operations.
 */

fun Iterable<Map<String, String>>.
convertToStudents(): Iterable<Student> {
    val res = mutableListOf<Student>()
    for (map in this) {
        res.add(map.toStudent())
    }
    return res
}

fun Iterable<Student>.convertToFirstName(): Iterable<String> {
    val res = mutableListOf<String>()
    for (std in this) {
        res.add(std.name.split(" ").first())
    }
    return res
}


fun Iterable<Student>.convertToSurnames(): Iterable<String> {
    val res = mutableListOf<String>()
    for (std in this) {
        res.add(std.name.split(" ").last())
    }
    return res
}

fun Iterable<String>.whereWordStartsWith(prefix: String): Iterable<String> {
    val res = mutableListOf<String>()
    for (word in this) {
        if(word.startsWith(prefix))
            res.add(word)
    }
    return res
}

fun Iterable<Student>.whereNumberIsGreaterThan(nr: Int): Iterable<Student> {
    val res = mutableListOf<Student>()
    for (std in this) {
        if(std.nr > nr)
            res.add(std)
    }
    return res
}
