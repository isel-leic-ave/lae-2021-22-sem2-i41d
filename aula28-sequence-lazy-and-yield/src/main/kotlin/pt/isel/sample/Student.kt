package pt.isel.sample

data class Student(val nr: Int, val name: String, val classroom: String)

fun Map<String, String>.toStudent() : Student {
    val nr: String = this["nr"] ?: throw Exception("Missing property nr in Map")
    val name: String = this["name"] ?: throw Exception("Missing property name in Map")
    val classroom: String = this["classroom"] ?: throw Exception("Missing property classroom in Map")
    return Student(nr.toInt(), name, classroom)
}
