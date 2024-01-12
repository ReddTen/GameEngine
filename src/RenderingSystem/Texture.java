package RenderingSystem;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.IOException;
import java.io.InputStream;

public class Texture {

    private int textureId;

    // Constructor to load texture from file
    public Texture(GLAutoDrawable drawable, String path) {
        try {
            // Load the texture file
            InputStream input = getClass().getResourceAsStream(path);
            com.jogamp.opengl.util.texture.Texture texture = TextureIO.newTexture(input, true, null);
            this.textureId = texture.getTextureObject();

            setup(drawable);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load a texture file: " + path, e);
        }
    }

    private void setup(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // Bind the texture
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureId);

        // Set texture parameters
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);

        // Unbind the texture
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
    }

    // Method to bind the texture for rendering
    public void bind(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureId);
    }

    // Method to unbind the texture
    public void unbind(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
    }
}
