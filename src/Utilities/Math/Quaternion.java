package Utilities.Math;

public class Quaternion {
    private float x, y, z, w; // Quaternion components

    // Constructor
    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    // Constructor for creating a quaternion from axis-angle
    public static Quaternion fromAxisAngle(Vector3D axis, float angle) {
        float halfAngle = angle / 2.0f;
        float sinHalfAngle = (float) Math.sin(halfAngle);

        float qx = axis.getX() * sinHalfAngle;
        float qy = axis.getY() * sinHalfAngle;
        float qz = axis.getZ() * sinHalfAngle;
        float qw = (float) Math.cos(halfAngle);

        return new Quaternion(qx, qy, qz, qw).normalize();
    }
    // Normalize the quaternion
    public Quaternion normalize() {
        float mag = (float) Math.sqrt(x * x + y * y + z * z + w * w);
        return new Quaternion(x / mag, y / mag, z / mag, w / mag);
    }

    // Multiply this quaternion by another
    public void multiply(Quaternion other) {
        float newX = this.w * other.x + this.x * other.w + this.y * other.z - this.z * other.y;
        float newY = this.w * other.y - this.x * other.z + this.y * other.w + this.z * other.x;
        float newZ = this.w * other.z + this.x * other.y - this.y * other.x + this.z * other.w;
        float newW = this.w * other.w - this.x * other.x - this.y * other.y - this.z * other.z;

        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;
    }

    // Convert this quaternion to a rotation matrix
    public Matrix4x4 toRotationMatrix() {
        Matrix4x4 matrix = new Matrix4x4();
        // Conversion implementation
        // Populate matrix with the appropriate values
        return matrix;
    }


    // Getters and Setters

    @Override
    public String toString() {
        return "Quaternion{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
