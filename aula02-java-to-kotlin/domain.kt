import kotlin.math.sqrt

data class Student(val nr: Int, val name: String)

data class Account(val balance: Double, val id: String)

data class Point(var x: Int, var y: Int) { // : Any(){ // Implict
    val module
        get() = sqrt((x*x + y*y).toDouble())
}

/**
 * public Point(int, int);
    Code:
       0: aload_0
       1: invokespecial #9                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: iload_1
       6: putfield      #13                 // Field x:I
       9: aload_0
      10: iload_2
      11: putfield      #16                 // Field y:I
      14: aload_0
      15: aload_0
 */