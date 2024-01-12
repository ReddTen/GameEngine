package Utilities.Math;

public class Intersect {

    // Method to check for intersection between two rays
    public static boolean rayRayIntersection(Ray ray1, Ray ray2) {
        // Constants for tolerance
        final float EPSILON = 0.0001f;

        Vector3D p1 = ray1.getOrigin();
        Vector3D d1 = ray1.getDirection().normalize();
        Vector3D p2 = ray2.getOrigin();
        Vector3D d2 = ray2.getDirection().normalize();

        Vector3D r = p1.subtract(p2);
        float a = d1.dot(d1); // Always >= 0
        float b = d1.dot(d2);
        float c = d2.dot(d2); // Always >= 0
        float d = d1.dot(r);
        float e = d2.dot(r);
        float f = r.dot(r);

        float denom = a * c - b * b;
        if (Math.abs(denom) < EPSILON) {
            // Lines are almost parallel
            return false;
        }

        float s = (b * e - c * d) / denom;
        float t = (a * e - b * d) / denom;

        // Check the distance at the closest points
        Vector3D closestPointOnRay1 = p1.add(d1.scale(s));
        Vector3D closestPointOnRay2 = p2.add(d2.scale(t));

        return closestPointOnRay1.subtract(closestPointOnRay2).magnitudeSquared() < EPSILON;
    }


    // Method to check for intersection between a ray and a plane
    public static boolean rayPlaneIntersection(Ray ray, Plane plane) {
        return plane.intersectsRay(ray);
    }

    // Method to check for intersection between a ray and a sphere
    public static boolean raySphereIntersection(Ray ray, Vector3D sphereCenter, float sphereRadius) {
        Vector3D oc = ray.getOrigin().subtract(sphereCenter);
        float a = ray.getDirection().magnitudeSquared();
        float b = 2.0f * oc.dot(ray.getDirection());
        float c = oc.magnitudeSquared() - sphereRadius * sphereRadius;
        float discriminant = b * b - 4 * a * c;

        return discriminant >= 0;
    }


    // Method to check for intersection between two spheres
    public static boolean sphereSphereIntersection(Vector3D center1, float radius1, Vector3D center2, float radius2) {
        float distance = Vector3D.distance(center1, center2);
        return distance < (radius1 + radius2);
    }

    // Additional intersection methods can be added for other shapes like boxes, cylinders, etc.

    // Private constructor to prevent instantiation
    private Intersect() {
    }
}