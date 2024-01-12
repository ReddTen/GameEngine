package Game;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class MyGameObject extends GameObject {

    public MyGameObject() {
        super();
        // Set the vertex count for a triangle
        setVertexCount(3);
    }

    @Override
    public void bindMeshData(GL2 gl) {
        // Normally, you would bind VBOs here, but for testing, we will directly define vertices.
    }

    @Override
    public void render(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // Use your shader program
        // shader.use(drawable); // Assuming you have a Shader class

        // Set the model matrix, etc.
        // float[] modelMatrix = getModelMatrix();
        // Pass the model matrix to the shader

        // Define a simple triangle
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f(-0.5f, -0.5f, 0); // Vertex 1
        gl.glVertex3f(0.5f, -0.5f, 0);  // Vertex 2
        gl.glVertex3f(0, 0.5f, 0);      // Vertex 3
        gl.glEnd();

        // Unuse your shader program
        // shader.unuse(drawable);
    }

    @Override
    public void update(float deltaTime) {
        // Update object logic, if any
    }

    @Override
    protected void onDestroy() {
        // Handle destruction logic, if any
    }
}
