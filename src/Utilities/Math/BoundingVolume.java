package Utilities.Math;

public class BoundingVolume {
    private Vector3D min; // Minimum corner of the bounding box
    private Vector3D max; // Maximum corner of the bounding box

    // Constructor
    public BoundingVolume(Vector3D min, Vector3D max) {
        this.min = min;
        this.max = max;
    }

    // Check if this bounding box intersects with another bounding box
    public boolean intersects(BoundingVolume other) {
        return (this.min.getX() <= other.max.getX() && this.max.getX() >= other.min.getX()) &&
                (this.min.getY() <= other.max.getY() && this.max.getY() >= other.min.getY()) &&
                (this.min.getZ() <= other.max.getZ() && this.max.getZ() >= other.min.getZ());
    }

    // Check if a point is inside the bounding box
    public boolean contains(Vector3D point) {
        return (point.getX() >= min.getX() && point.getX() <= max.getX()) &&
                (point.getY() >= min.getY() && point.getY() <= max.getY()) &&
                (point.getZ() >= min.getZ() && point.getZ() <= max.getZ());
    }

    // Getters and setters
    public Vector3D getMin() {
        return min;
    }

    public void setMin(Vector3D min) {
        this.min = min;
    }

    public Vector3D getMax() {
        return max;
    }

    public void setMax(Vector3D max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
