package pt.isel

class Student(val nr: Int, val name: String, var addr: Address?) {
    data class Address(val nr: Int, val road: String)
}
