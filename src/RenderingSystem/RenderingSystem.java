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
        clearScreen();

        shader.use(scene.getGLAutoDrawable());

        // Set up camera and view projection

        // Render each object in the scene
        for (GameObject gameObject : scene.getGameObjects()) {
            renderGameObject(gameObject, scene.getAutoDrawable());
        }

        shader.destroy(scene.getGLAutoDrawable());
    }

    private void clearScreen(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    }

    private void renderGameObject(GameObject gameObject) {
        // Prepare model transformation

        // Bind the shader program

        // Set shader uniforms (e.g., transformation matrices, material properties)

        // Bind vertex data and draw the object

        // Unbind shader program
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