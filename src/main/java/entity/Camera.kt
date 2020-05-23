package entity

import org.joml.Vector3f
import org.lwjgl.glfw.GLFW
import renderEngine.DisplayManager
import utils.Keyboard


class Camera(speed: Float = 25f) {
    val position = Vector3f(0f, 0f, 0f)
    val pitch = 0f
    val yaw = 0f
    val roll = 0f
    var SPEED = speed

    init {
        val keyboard = Keyboard()
        GLFW.glfwSetKeyCallback(DisplayManager.getWindow(), keyboard::invoke)
    }

    fun move() {
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
            position.x += 0.02f * SPEED
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
            position.x -= 0.02f * SPEED
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
            position.z += 0.02f * SPEED
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
            position.z -= 0.02f * SPEED
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            position.y += 0.02f * SPEED
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_TAB)) {
            position.y -= 0.02f * SPEED
        }
    }

}