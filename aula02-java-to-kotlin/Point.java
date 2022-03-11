import java.lang.Math;

public class Point {
    // private final int x, y;
    private int x, y;

    Point(int a, int b){
        // super(); // Implicit
        this.x = a;
        this.y = b;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public void setX(int v) {x = v;}

    public void setY(int v) {y = v;}


    public double getModule(){
        return Math.sqrt(x * x + y * y);
    }
}