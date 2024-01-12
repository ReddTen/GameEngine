package Utilities.Math;

public class Circle {
    private float x;
    private float y;
    private float radius;

    // Constructor
    public Circle(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    // Check if this circle intersects with another circle
    public boolean intersects(Circle other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < this.radius + other.radius;
    }

    // Check if a point is inside the circle
    public boolean contains(float px, float py) {
        float dx = this.x - px;
        float dy = this.y - py;
        return (dx * dx + dy * dy) <= (radius * radius);
    }

    // Getters and setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}