data class Student(val name: String)

data class Point(val x: Int, val y: Int)

fun main() {
    val stds1: List<Any> = listOf(Student("Ze"), Point(5, 7))
    val s1 = stds1[0] as Student
    println(s1.name)

    val stds2: List<Student> = listOf(Student("Maria")) // Comiplation Error with Point(5, 7))
    val s2 = stds2[0]
    println(s2.name)
}
