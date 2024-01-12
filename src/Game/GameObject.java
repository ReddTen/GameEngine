package Game;

import Utilities.Math.Vector3D;
import Utilities.Math.Quaternion;
import Utilities.Math.Matrix4x4;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public abstract class GameObject {
    // Fields for VBO, IBO, and other mesh data
    protected int vertexBufferId;
    protected int indexBufferId;
    protected int vertexCount;
    private Vector3D position;
    private Quaternion rotation;
    private Vector3D scale;

    private Vector3D velocity;
    private Vector3D acceleration;
    private float mass;

    protected int healthPoints;

    public GameObject() {
        this.vertexCount = 0; // Default to 0, can be set later
        position = new Vector3D(0, 0, 0);
        rotation = new Quaternion(0, 0, 0, 1); // Initialize to identity
        scale = new Vector3D(1, 1, 1);
        velocity = new Vector3D(0, 0, 0);
        acceleration = new Vector3D(0, 0, 0);
        mass = 1.0f;
        healthPoints = 100;
    }

    public float[] getModelMatrix() {
        Matrix4x4 translationMatrix = Matrix4x4.createTranslation(position.getX(), position.getY(), position.getZ());
        Matrix4x4 rotationMatrix = rotation.toRotationMatrix();
        Matrix4x4 scaleMatrix = Matrix4x4.createScaling(scale.getX(), scale.getY(), scale.getZ());

        Matrix4x4 modelMatrix = Matrix4x4.identity();
        modelMatrix = Matrix4x4.multiply(modelMatrix, translationMatrix);
        modelMatrix = Matrix4x4.multiply(modelMatrix, rotationMatrix);
        modelMatrix = Matrix4x4.multiply(modelMatrix, scaleMatrix);

        return modelMatrix.toFloatArray(); // Convert the matrix to a float array
    }


    public void update(float deltaTime) {
        Vector3D deltaPosition = velocity.scale(deltaTime);
        position = position.add(deltaPosition);

        Vector3D deltaVelocity = acceleration.scale(deltaTime);
        velocity = velocity.add(deltaVelocity);

        acceleration = new Vector3D(0, 0, 0);
    }

    public abstract void render(GLAutoDrawable drawable);

    public void bindMeshData(GL2 gl) {
        // Bind the vertex buffer
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vertexBufferId);
        // Specify the layout of the vertex data
        int stride = 0; // The stride depends on your vertex format
        gl.glVertexAttribPointer(0, 3, GL2.GL_FLOAT, false, stride, 0); // Position attribute
        gl.glEnableVertexAttribArray(0);
        // Repeat for other vertex attributes like normals, texture coordinates, etc.

        // Bind the index buffer if you have one
        if (indexBufferId > 0) {
            gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, indexBufferId);
        }

        // Unbind the VBO
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
    }

    // ... additional methods ...

    public void setVertexCount(int count) {
        this.vertexCount = count;
    }
    public int getVertexCount() {
        return this.vertexCount;
    }

    public void unbindMeshData(GL2 gl) {
        // You can add OpenGL commands to unbind any mesh data (VBOs, VAOs, etc.) here
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0); // Unbind VBOs if used
        gl.glBindVertexArray(0); // Unbind VAOs if used
        gl.glDisableVertexAttribArray(0); // Disable vertex attribute arrays if used
    }

    public void onCollision(GameObject otherObject) {
        // Collision response
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints <= 0) {
            onDestroy();
        }
    }

    protected abstract void onDestroy();

    // Getters and setters
    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

    public Vector3D getScale() {
        return scale;
    }

    public void setScale(Vector3D scale) {
        this.scale = scale;
    }

    // ... Additional getters and setters for velocity, acceleration, mass, etc.
}
