package RenderingSystem;
import Utilities.Math.Matrix4x4;


public class Camera {

    private final float[] position;
    private final float[] rotation;

    private final Matrix4x4 projectionMatrix;
    private final Matrix4x4 viewMatrix;

    // Constructor to initialize the camera
    public Camera() {
        position = new float[]{0.0f, 0.0f, 0.0f};
        rotation = new float[]{0.0f, 0.0f, 0.0f};

        projectionMatrix = new Matrix4x4();
        viewMatrix = new Matrix4x4();
    }

    // Method to update the camera's view matrix
    public void updateViewMatrix() {
        viewMatrix.loadIdentity();

        // Create rotation matrices for each axis
        Matrix4x4 rotationX = Matrix4x4.createRotationX(rotation[0]);
        Matrix4x4 rotationY = Matrix4x4.createRotationY(rotation[1]); // Implement this method in Matrix4x4
        Matrix4x4 rotationZ = Matrix4x4.createRotationZ(rotation[2]); // Implement this method in Matrix4x4

        // Combine rotations and apply them to viewMatrix
        viewMatrix.multiply(rotationX);
        viewMatrix.multiply(rotationY);
        viewMatrix.multiply(rotationZ);

        // Create and apply translation matrix
        Matrix4x4 translation = Matrix4x4.createTranslation(-position[0], -position[1], -position[2]);
        viewMatrix.multiply(translation);
    }

    // Setters for position and rotation
    public void setPosition(float x, float y, float z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    public void setRotation(float x, float y, float z) {
        rotation[0] = x;
        rotation[1] = y;
        rotation[2] = z;
    }

    // Getters for position, rotation, and matrices
    public float[] getPosition() {
        return position;
    }

    public float[] getRotation() {
        return rotation;
    }

    public Matrix4x4 getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4x4 getViewMatrix() {
        return viewMatrix;
    }

    // Method to set up a perspective projection matrix
    public void setPerspectiveProjection(float fov, float aspect, float zNear, float zFar) {
        // Implement perspective projection method in Matrix4x4 and use it here
        projectionMatrix.loadPerspective(fov, aspect, zNear, zFar); // Example method name
    }
}