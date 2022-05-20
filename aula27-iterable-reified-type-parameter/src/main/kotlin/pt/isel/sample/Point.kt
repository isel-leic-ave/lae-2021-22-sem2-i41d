package pt.isel.sample

import kotlin.math.sqrt

data class Point(var x: Int, var y: Int) { // : Any(){ // Implict
    val module
        get() = sqrt((x*x + y*y).toDouble())
    var z: Int = 0
}
