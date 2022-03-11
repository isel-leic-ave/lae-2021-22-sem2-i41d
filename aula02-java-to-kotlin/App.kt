/**
 * Se colocarmos em comentário a chamada a playWithPoint()
 * - Compilar? Continuamos a precisar do Point.class da Metadata
 * - Executar? Precisamos de Point.class? 
 */
fun main() {
    playWithPoint()

    println("Hello")
}

fun playWithPoint() {
    val p = Point(5, 7)
    print("Module of Point(5,7) = ")
    println(p.module)
    print("Coordenate x = ")
    println(p.x)
}