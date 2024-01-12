package Utilities.Math;

public class Ray {
    private Vector3D origin;
    private Vector3D direction;

    // Constructor
    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
        this.direction.normalize(); // Ensure the direction is a unit vector
    }

    public static Ray fromTwoPoints(Vector3D point1, Vector3D point2) {
        Vector3D direction = point2.subtract(point1).normalize();
        return new Ray(point1, direction);
    }

    public static Vector3D closestPointOnRay(Ray ray, Vector3D point) {
        Vector3D originToPoint = point.subtract(ray.getOrigin());
        float t = originToPoint.dot(ray.getDirection());
        return ray.getPoint(t);
    }

    public static float distanceFromPointToRay(Vector3D point, Ray ray) {
        Vector3D closestPoint = closestPointOnRay(ray, point);
        return closestPoint.subtract(point).magnitude();
    }

    // Method to get a point along the ray at a certain distance
    public Vector3D getPoint(float distance) {
        return new Vector3D(
                origin.getX() + direction.getX() * distance,
                origin.getY() + direction.getY() * distance,
                origin.getZ() + direction.getZ() * distance
        );
    }

    // Getters and setters
    public Vector3D getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3D origin) {
        this.origin = origin;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
        this.direction.normalize();
    }



    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }
}