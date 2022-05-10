fun main() {
    val s = Student("Maria")
    val obj: Any = s
    foo(obj)
    foo("Manel") // Compila se Erros. Runtime ???
    val nr: Int = 731
    foo(nr)
}

fun boxing(nr: Int) : Any { return nr }

fun unboxing(nr: Int?) : Int { return nr ?: throw Exception("Null not supported for primitive Int!") }

fun foo(obj: Any) {
    val s2: Student = obj as Student
    print(s2.name)
}

data class Student(val name: String)