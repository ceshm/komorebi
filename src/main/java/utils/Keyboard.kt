package utils

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWKeyCallback

class Keyboard : GLFWKeyCallback() {
    override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
        keys[key] = action != GLFW.GLFW_RELEASE
    }

    companion object {
        var keys = BooleanArray(65536)
        fun isKeyDown(keycode: Int): Boolean {
            return keys[keycode]
        }
    }
}