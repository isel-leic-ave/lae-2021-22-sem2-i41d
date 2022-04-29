package pt.isel

class AddressFormatter : Formatter {

    override fun format(v: Any?) : Any? {
        val addr = v as Student.Address
        return "${addr.road}, nr ${addr.nr}"
    }
}
