package Utilities.Math;

public class Vector2D {
    private float x;
    private float y;

    // Constructor
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
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

    // Add another vector to this one
    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    // Subtract another vector from this one
    public void subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    // Multiply this vector by a scalar
    public void scale(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    // Calculate the magnitude of this vector
    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    // Normalize this vector
    public void normalize() {
        float magnitude = magnitude();
        if (magnitude != 0) {
            scale(1.0f / magnitude);
        }
    }

    // Calculate the dot product of this vector and another
    public float dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    // Static method to calculate the distance between two vectors
    public static float distance(Vector2D v1, Vector2D v2) {
        return (float) Math.sqrt(Math.pow(v2.x - v1.x, 2) + Math.pow(v2.y - v1.y, 2));
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}