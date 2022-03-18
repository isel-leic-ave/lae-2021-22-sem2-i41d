package pt.isel


fun main() {
    println(Point(5,7))
    println(Student(763541, "Ze Manel", null))

    val logger = Logger()
    logger.log(Point(5,7))
    logger.log(Student(763541, "Ze Manel", null))
}
