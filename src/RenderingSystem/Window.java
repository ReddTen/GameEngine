package RenderingSystem;

import com.jogamp.opengl.*;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Window {
    private final JFrame frame;
    private final GLCanvas glCanvas;
    private final int width;
    private final int height;

    public Window(String title, int width, int height) {
        this.width = width;
        this.height = height;

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        glCanvas = new GLCanvas(capabilities);

        frame = new JFrame(title);
        frame.getContentPane().add(glCanvas);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FPSAnimator animator = new FPSAnimator(glCanvas, 60);
        animator.start();
    }

    public void show() {
        frame.setVisible(true);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GLCanvas getGLCanvas() {
        return glCanvas;
    }

    // Additional methods as needed...
}