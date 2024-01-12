package RenderingSystem;

import com.jogamp.opengl.*;


public class GraphicsContext implements GLEventListener {
    private int windowWidth;
    private int windowHeight;

    public GraphicsContext(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        // Initialize OpenGL state (e.g., set clear color, depth testing)
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Example: Set clear color
        // Enable depth testing
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);

        // Enable face culling
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glCullFace(GL2.GL_BACK);

        // Setup blending (for transparent objects)
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

        // Set the viewport
        gl.glViewport(0, 0, windowWidth, windowHeight);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // Cleanup resources
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        // Render frame
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        // More rendering code here
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Handle window resizing
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height); // Example: Set viewport size
        // More resizing handling code here
    }
}