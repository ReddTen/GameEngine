package Utilities.Math;

import java.util.List;
import java.util.Random;

public class GameRandom {
    private static final Random random = new Random();

    // Generate a random float between 0.0 and 1.0
    public static float nextFloat() {
        return random.nextFloat();
    }

    // Generate a random float in a specific range
    public static float nextFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    // Generate a random int in a specific range
    public static int nextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    // Generate a random boolean
    public static boolean nextBoolean() {
        return random.nextBoolean();
    }

    // Choose a random element from a list
    public static <T> T choose(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(random.nextInt(list.size()));
    }

    // Generate a random 2D vector with specified magnitude
    public static Vector2D randomVector2D(float magnitude) {
        float angle = nextFloat(0, (float) Math.PI * 2);
        return new Vector2D((float) Math.cos(angle) * magnitude, (float) Math.sin(angle) * magnitude);
    }

    // Generate a random 3D vector with specified magnitude
    public static Vector3D randomVector3D(float magnitude) {
        float angle = nextFloat(0, (float) Math.PI * 2);
        float z = nextFloat(-1.0f, 1.0f);
        float zScale = (float) Math.sqrt(1.0f - z * z) * magnitude;
        return new Vector3D(zScale * (float) Math.cos(angle), zScale * (float) Math.sin(angle), z * magnitude);
    }

    // Additional random utility methods can be added as needed
}