package Utilities.Math;

public class Plane {
    private Vector3D normal;
    private float distance;

    // Constructor
    public Plane(Vector3D normal, float distance) {
        this.normal = normal;
        this.normal.normalize(); // Ensure the normal is a unit vector
        this.distance = distance;
    }

    // Method to check on which side of the plane a point lies
    public float side(Vector3D point) {
        return normal.dot(point) + distance;
    }

    // Check if a ray intersects this plane
    public boolean intersectsRay(Ray ray) {
        float denominator = normal.dot(ray.getDirection());
        if (Math.abs(denominator) > 1e-6) { // Ensure not parallel (avoid division by zero)
            float t = -(normal.dot(ray.getOrigin()) + distance) / denominator;
            return t >= 0; // Intersection occurs if t is non-negative
        }
        return false; // Ray is parallel to the plane
    }

    // Getters and setters
    public Vector3D getNormal() {
        return normal;
    }

    public void setNormal(Vector3D normal) {
        this.normal = normal;
        this.normal.normalize();
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "normal=" + normal +
                ", distance=" + distance +
                '}';
    }
}
