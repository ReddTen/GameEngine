package RenderingSystem;

import com.jogamp.opengl.*;
import Game.GameObject;
import Game.Scene;


public class RenderingSystem {
    private Shader shader;
    private GraphicsContext graphicsContext;

    public RenderingSystem(Window window) {
        // Assume Window class has methods to get width and height
        this.graphicsContext = new GraphicsContext(window.getWidth(), window.getHeight());
        window.getGLCanvas().addGLEventListener(graphicsContext);
        // Additional initialization...
    }

    // Initialize the rendering system
    public void initialize(GLAutoDrawable drawable, Scene scene) {
        scene.setGLAutoDrawable(drawable);
        GL2 gl = drawable.getGL().getGL2();
        // Create and initialize the shader

        shader = new Shader("Resources/Shaders/BasicVertexShader.glsl", "Resources/Shaders/BasicFragementShader.glsl", drawable);
        shader.create(drawable);

        // Setup initial render states
        gl.glEnable(GL2.GL_DEPTH_TEST); // Example: Enable depth testing
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glEnable(GL2.GL_CULL_FACE);  // Example: Enable backface culling
        gl.glCullFace(GL2.GL_BACK);
    }
    // Render a frame
    public void render(Scene scene) {
        // Clear the screen
        GLAutoDrawable drawable = scene.getGLAutoDrawable();
        clearScreen(drawable);

        shader.use(drawable);

        // Set up camera and view projection

        // Render each object in the scene
        for (GameObject gameObject : scene.getGameObjects()) {
            renderGameObject(gameObject,drawable);
        }

        shader.destroy(drawable);
    }

    private void clearScreen(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    }

    private void renderGameObject(GameObject gameObject, GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // Assuming your GameObject has methods to get its model matrix
        float[] modelMatrix = gameObject.getModelMatrix();

        // Bind the shader program
        shader.use(drawable);

        // Set shader uniforms
        // Assuming you have methods in your Shader class to set uniforms
        shader.setUniformMatrix("modelMatrix", modelMatrix, GLAutoDrawable drawable);
        // Set other uniforms as needed, such as view and projection matrices

        // Bind vertex data
        // Assuming your GameObject class has a method to bind its mesh data
        gameObject.bindMeshData(gl);

        // Draw the object
        // This could vary depending on how your GameObject's mesh data is stored
        gl.glDrawArrays(GL2.GL_TRIANGLES, 0, gameObject.getVertexCount());

        // Unbind shader program if necessary
        shader.unuse(drawable);

        // Unbind any bound resources to the GameObject if necessary
        gameObject.unbindMeshData(gl);
    }


    // Resize the viewport
    public void resizeViewport(int width, int height, GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        // Additional logic to handle aspect ratio, etc.
    }

    // Clean up resources
    public void cleanup(GLAutoDrawable drawable) {
        shader.destroy(drawable);
        // Cleanup other resources...
    }

    // Additional methods for managing shaders, textures, etc.
}