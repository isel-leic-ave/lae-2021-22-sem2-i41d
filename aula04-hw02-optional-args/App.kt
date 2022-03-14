fun main() {
    val p1 = Point(5, 7)
    val p2 = Point(3, 9)
    println("Distance $p1 to $p2 = ${p1.distance(p2)}")
    println("Distance $p1 to (0,0) = ${p1.distance()}")
}