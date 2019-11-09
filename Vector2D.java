public class Vector2D{
    public int x, y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void set(Vector2D v){
        
    }
    public boolean equals(Vector2D v){
        return (v.x == x && v.y == y);
    }
    public double distanceTo(Vector2D other){
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }
    public Vector2D normalize(){
        return new Vector2D(x, y);
    }
    public Vector2D plus(Vector2D other){
        return new Vector2D(x + other.x, y + other.y);
    }
    public Vector2D minus(Vector2D other){
        return new Vector2D(x - other.x, y - other.y);
    }
}
