package RenderingSystem;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;

public class Shader {

    private int programId;
    private int vertexShaderId;
    private int fragmentShaderId;
    private String vertexShaderCode;
    private String fragmentShaderCode;

    public Shader(String vertexShaderPath, String fragmentShaderPath, GLAutoDrawable drawable) {
        this.vertexShaderCode = ShaderUtils.loadShader(vertexShaderPath);
        this.fragmentShaderCode = ShaderUtils.loadShader(fragmentShaderPath);
    }
    private void createShader(String vertexCode, String fragmentCode, GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // Load and compile shaders
        vertexShaderId = loadShader(vertexCode, GL2.GL_VERTEX_SHADER, gl);
        fragmentShaderId = loadShader(fragmentCode, GL2.GL_FRAGMENT_SHADER, gl);

        // Rest of the shader creation code...
    }
    private int loadShader(String code, int type, GL2 gl) {
        // Create and compile shader
        int shaderId = gl.glCreateShader(type);
        gl.glShaderSource(shaderId, 1, new String[]{code}, null);
        gl.glCompileShader(shaderId);

        // Check for compilation errors
        int[] compiled = new int[1];
        gl.glGetShaderiv(shaderId, GL2.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            // Handle shader compilation error
            int[] logLength = new int[1];
            gl.glGetShaderiv(shaderId, GL2.GL_INFO_LOG_LENGTH, logLength, 0);

            byte[] log = new byte[logLength[0]];
            gl.glGetShaderInfoLog(shaderId, logLength[0], (int[])null, 0, log, 0);

            String logMessage = new String(log);
            System.err.println("Error compiling the shader: " + logMessage);
            throw new RuntimeException("Error compiling shader");
        }

        return shaderId;
    }

    // This method sets a uniform matrix in the active shader program
// Inside your Shader class or wherever you manage OpenGL rendering
    public void setUniformMatrix(String uniformName, float[] matrix, GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();

        // Get the location (ID) of the uniform variable in the shader program
        int uniformLocation = gl.glGetUniformLocation(programId, uniformName);

        // Check if the uniform variable exists in the shader
        if (uniformLocation == -1) {
            // Handle the case where the uniform doesn't exist or has been optimized out
            System.err.println("Uniform " + uniformName + " not found in shader program.");
            return;
        }

        // Set the value of the uniform variable
        gl.glUniformMatrix4fv(uniformLocation, 1, false, matrix, 0);
    }

    public void create(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // Load and compile vertex and fragment shaders
        vertexShaderId = loadShader(this.vertexShaderCode, GL2.GL_VERTEX_SHADER, gl);
        fragmentShaderId = loadShader(this.fragmentShaderCode, GL2.GL_FRAGMENT_SHADER, gl);

        // Create a program and attach shaders
        programId = gl.glCreateProgram();
        gl.glAttachShader(programId, vertexShaderId);
        gl.glAttachShader(programId, fragmentShaderId);

        // Link the program and check for errors
        gl.glLinkProgram(programId);
        int[] linked = new int[1];
        gl.glGetProgramiv(programId, GL2.GL_LINK_STATUS, linked, 0);
        if (linked[0] == 0) {
            // Handle program linking error
            int[] logLength = new int[1];
            gl.glGetProgramiv(programId, GL2.GL_INFO_LOG_LENGTH, logLength, 0);

            byte[] log = new byte[logLength[0]];
            gl.glGetProgramInfoLog(programId, logLength[0], (int[])null, 0, log, 0);

            String logMessage = new String(log);
            System.err.println("Error linking the program: " + logMessage);
            throw new RuntimeException("Error linking program");
        }
    }

    public void use(GLAutoDrawable drawable) {
        // Activate the shader program
        GL2 gl = drawable.getGL().getGL2();
        gl.glUseProgram(programId);
    }

    public void destroy(GLAutoDrawable drawable) {
        // Cleanup and detach shaders
        GL2 gl = drawable.getGL().getGL2();
        gl.glDetachShader(programId, vertexShaderId);
        gl.glDetachShader(programId, fragmentShaderId);
        gl.glDeleteShader(vertexShaderId);
        gl.glDeleteShader(fragmentShaderId);
        gl.glDeleteProgram(programId);
    }
}