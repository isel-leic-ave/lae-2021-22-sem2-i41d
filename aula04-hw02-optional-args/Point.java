import java.lang.Math;

public class Point{
    private final int x, y;
    
    private static final Point root = new Point(0,0);

    Point(int a, int b){
        this.x = a;
        this.y = b;
    }

    public int getX() {return this.x;}

    public int getY() {return this.y;}

    @Override
    public String toString() {
        return "Point(x=" + x + ", y=" + y +")";
    }

    public double distance(Point other ){
        int dX = this.x - other.x;
        int dY = this.y - other.y;
        return Math.sqrt(dX*dX + dY*dY);
    }

    public double distance(){
        return distance(root);
    }
}