package Utilities.Math;


public class Color {
    private float r; // Red component
    private float g; // Green component
    private float b; // Blue component
    private float a; // Alpha component

    // Constructor
    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    // Create a color from an RGBA integer
    public static Color fromRGBA(int rgba) {
        float r = ((rgba >> 24) & 0xFF) / 255f;
        float g = ((rgba >> 16) & 0xFF) / 255f;
        float b = ((rgba >> 8) & 0xFF) / 255f;
        float a = (rgba & 0xFF) / 255f;
        return new Color(r, g, b, a);
    }

    // Getters and setters
    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    // Linearly interpolate between two colors
    public static Color lerp(Color a, Color b, float t) {
        return new Color(
                a.r + (b.r - a.r) * t,
                a.g + (b.g - a.g) * t,
                a.b + (b.b - a.b) * t,
                a.a + (b.a - a.a) * t
        );
    }

    // Convert the color to an RGBA integer
    public int toRGBA() {
        int rInt = (int)(r * 255) & 0xFF;
        int gInt = (int)(g * 255) & 0xFF;
        int bInt = (int)(b * 255) & 0xFF;
        int aInt = (int)(a * 255) & 0xFF;
        return (rInt << 24) | (gInt << 16) | (bInt << 8) | aInt;
    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
