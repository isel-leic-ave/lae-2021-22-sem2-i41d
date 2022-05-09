package pt.isel

class Student(
    @ToLog val nr: Int,
    @ToLog val name: String,
    @ToLog(AddressFormatter::class) var addr: Address?) {
    data class Address(val nr: Int, val road: String)
}
