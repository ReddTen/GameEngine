package Utilities.Math;

public class Transform {
    private Vector3D position;
    private Quaternion rotation;
    private Vector3D scale;

    // Constructor
    public Transform() {
        position = new Vector3D(0, 0, 0);
        rotation = new Quaternion(0, 0, 0, 1); // Identity quaternion
        scale = new Vector3D(1, 1, 1); // Default scale is 1
    }

    // Apply translation to the position
    public void translate(float dx, float dy, float dz) {
        position.add(new Vector3D(dx, dy, dz));
    }

    // Apply rotation
    public void rotate(Quaternion deltaRotation) {
        rotation.multiply(deltaRotation);
        rotation.normalize();
    }

    // Apply scaling
    public void scale(float sx, float sy, float sz) {
        scale.setX(scale.getX() * sx);
        scale.setY(scale.getY() * sy);
        scale.setZ(scale.getZ() * sz);
    }

    // Getters and setters for position, rotation, and scale

    // Method to get the transformation matrix
    public Matrix4x4 getTransformationMatrix() {
        Matrix4x4 translationMatrix = Matrix4x4.createTranslation(position.getX(), position.getY(), position.getZ());
        Matrix4x4 rotationMatrix = rotation.toRotationMatrix();
        Matrix4x4 scaleMatrix = Matrix4x4.createScaling(scale.getX(), scale.getY(), scale.getZ());

        Matrix4x4 transformationMatrix = new Matrix4x4();
        transformationMatrix.loadIdentity();
        transformationMatrix.multiply(translationMatrix);
        transformationMatrix.multiply(rotationMatrix);
        transformationMatrix.multiply(scaleMatrix);

        return transformationMatrix;
    }

    @Override
    public String toString() {
        return "Transform{" +
                "position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                '}';
    }
}