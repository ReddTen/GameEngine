package Utilities.Math;

public class MathUtils {
    public static final float PI = 3.14159265358979323846f;
    public static final float DEG2RAD = PI / 180.0f;
    public static final float RAD2DEG = 180.0f / PI;

    // Clamp a value between a minimum and a maximum
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    // Linear interpolation between two values
    public static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    // Convert degrees to radians
    public static float toRadians(float degrees) {
        return degrees * DEG2RAD;
    }

    // Convert radians to degrees
    public static float toDegrees(float radians) {
        return radians * RAD2DEG;
    }

    // Smoothstep interpolation
    public static float smoothstep(float edge0, float edge1, float x) {
        float t = clamp((x - edge0) / (edge1 - edge0), 0.0f, 1.0f);
        return t * t * (3 - 2 * t);
    }

    // More utility functions can be added as needed

    // Private constructor to prevent instantiation
    private MathUtils() {
    }
}