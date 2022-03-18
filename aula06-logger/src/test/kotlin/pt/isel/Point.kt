package pt.isel

import kotlin.math.sqrt

data class Point(val x: Int, val y: Int) {
    fun distance(other: Point = Point(0,0)) : Double {
        val dX = x - other.x
        val dY = y - other.y
        return sqrt((dX*dX + dY*dY).toDouble())
    }
}
