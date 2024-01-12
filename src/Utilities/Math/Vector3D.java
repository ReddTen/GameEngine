package Utilities.Math;

public class Vector3D {
    public float x, y, z;

    // Constructor
    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Getters and setters
    public  float getX() {
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    // Add another vector to this one
    public static Vector3D add(Vector3D a, Vector3D b) {
        return new Vector3D(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    // Instance method for vector addition
    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    // Static method for vector subtraction
    public static Vector3D subtract(Vector3D a, Vector3D b) {
        return new Vector3D(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    // Instance method for vector subtraction
    public Vector3D subtract(Vector3D other) {
        return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    //Static method for scale
    public static Vector3D scale(Vector3D v, float scaleFactor) {
        return new Vector3D(v.x * scaleFactor, v.y * scaleFactor, v.z * scaleFactor);
    }
    public Vector3D scale(float scalar) {
        // Modify this vector and return a new vector
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }


    // Calculate the magnitude of this vector
    public static float magnitude(Vector3D v) {
        return (float) Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }
    // Instance method for calculating magnitude
    public float magnitude() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    // Static method for normalizing a vector
    public static Vector3D normalize(Vector3D v) {
        float length = (float) Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
        return new Vector3D(v.x / length, v.y / length, v.z / length);
    }
    // Instance method for normalizing the vector
    public Vector3D normalize() {
        float length = magnitude();
        return new Vector3D(this.x / length, this.y / length, this.z / length);
    }

    // Static method for the dot product of two vectors
    public static float dot(Vector3D a, Vector3D b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    // Instance method for calculating dot product
    public float dot(Vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    // Static method for the cross product of two vectors
    public static Vector3D cross(Vector3D a, Vector3D b) {
        return new Vector3D(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x
        );
    }

    // Instance method for calculating cross product
    public Vector3D cross(Vector3D other) {
        return new Vector3D(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }

    // Static method to calculate the distance between two vectors
    public static float distance(Vector3D v1, Vector3D v2) {
        return (float) Math.sqrt(Math.pow(v2.x - v1.x, 2) + Math.pow(v2.y - v1.y, 2) + Math.pow(v2.z - v1.z, 2));
    }

    // Instance method for calculating distance to another vector
    public float distance(Vector3D other) {
        return subtract(other).magnitude();
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public float magnitudeSquared() {
        return x * x + y * y + z * z;
    }
}