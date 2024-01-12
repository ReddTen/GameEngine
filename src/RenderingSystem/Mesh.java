package RenderingSystem;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Mesh {

    private final int[] vaoId = new int[1];
    private final int[] vboId = new int[1];
    private final int vertexCount;

    // Constructor to initialize the mesh with vertex data
    public Mesh(GLAutoDrawable drawable, float[] vertices) {
        this.vertexCount = vertices.length / 3; // Assuming each vertex has 3 components (x, y, z)

        GL2 gl = drawable.getGL().getGL2();

        // Generate and bind a Vertex Array Object (VAO)
        gl.glGenVertexArrays(1, vaoId, 0);
        gl.glBindVertexArray(vaoId[0]);

        // Generate and bind a Vertex Buffer Object (VBO)
        gl.glGenBuffers(1, vboId, 0);
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vboId[0]);

        // Create a buffer with the vertex data
        java.nio.FloatBuffer verticesBuffer = java.nio.FloatBuffer.wrap(vertices);

        // Upload the vertex data to the VBO
        gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * Float.BYTES, verticesBuffer, GL2.GL_STATIC_DRAW);

        // Define the structure of the vertex data
        gl.glVertexAttribPointer(0, 3, GL2.GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(0);

        // Unbind the VBO and VAO
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
        gl.glBindVertexArray(0);
    }

    // Method to render the mesh
    public void render(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // Bind the VAO
        gl.glBindVertexArray(vaoId[0]);

        // Draw the vertices
        gl.glDrawArrays(GL2.GL_TRIANGLES, 0, vertexCount);

        // Unbind the VAO
        gl.glBindVertexArray(0);
    }

    // Method to clean up resources
    public void cleanup(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // Disable the VBO index from the VAO attributes list
        gl.glDisableVertexAttribArray(0);

        // Delete the VBO
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
        gl.glDeleteBuffers(1, vboId, 0);

        // Delete the VAO
        gl.glBindVertexArray(0);
        gl.glDeleteVertexArrays(1, vaoId, 0);
    }
}
