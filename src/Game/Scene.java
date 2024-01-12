package Game;

import RenderingSystem.Camera;
import com.jogamp.opengl.*;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    private GLAutoDrawable glAutoDrawable;
    private final List<GameObject> gameObjects;
    private Camera camera;

    public void setGLAutoDrawable(GLAutoDrawable drawable) {
        this.glAutoDrawable = drawable;
    }
    public GLAutoDrawable getGLAutoDrawable() {
        return glAutoDrawable;
    }


    // Constructor to initialize the scene
    public Scene() {
        gameObjects = new ArrayList<>();
    }

    // Method to add a game object to the scene
    public void addGameObject(GameObject gameObject) {

        gameObjects.add(gameObject);
    }

    // Method to remove a game object from the scene
    public void removeGameObject(GameObject gameObject) {

        gameObjects.remove(gameObject);
    }

    // Method to set the camera for the scene
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    // Getter for the camera
    public Camera getCamera() {
        return camera;
    }

    // Method to update the scene
    public void update(float deltaTime) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(deltaTime);
        }
    }

    // Method to get all game objects in the scene
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects); // Return a copy to avoid external modification
    }

    // Method to render the scene
    public void render(GLAutoDrawable drawable) {
        if (camera != null) {
            camera.updateViewMatrix();
            // Set up rendering based on camera view
        }

        for (GameObject gameObject : gameObjects) {
            gameObject.render(drawable);
        }
    }
}