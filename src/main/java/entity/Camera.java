package entity;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import renderEngine.DisplayManager;
import utils.Keyboard;

/**
 * Holds the state of the 'camera' aka the user's point of view
 *
 * @autor ceshm
 *
 */
public class Camera {

    private Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;
    private static float SENSITIVITY = 25;

    public Camera(){
        Keyboard keyboard = new Keyboard();
        GLFW.glfwSetKeyCallback(DisplayManager.getWindow(), keyboard::invoke);
    }

    public void move() {
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
            position.z -= 0.02f*SENSITIVITY;
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
            position.x += 0.02f*SENSITIVITY;
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
            position.x -= 0.02f*SENSITIVITY;
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
            position.z += 0.02f*SENSITIVITY;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
