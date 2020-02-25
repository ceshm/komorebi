package renderEngine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static java.lang.Thread.sleep;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;


/**
 * Display manager using GLFW for window capabilities
 *
 * @autor ceshm
 *
 */
public class DisplayManager {

    private static long window;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final String TITLE = "Leaf";
    private static final int FPS_CAP = 120;

    public static long createDisplay() {
        if(!glfwInit()){
            System.out.println("GLFW Failed to initialize");
            System.exit(1);
        }
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, 0, 0);
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GL.createCapabilities();

        // this turns vsync on ???
        glfwSwapInterval(1);

        return window;
    }

    public static void updateDisplay() {
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    public static void closeDisplay() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        //glfwSetErrorCallback(null).free(); <<------------ this gives me an error for some reason
    }

    public static long getWindow(){
        return window;
    }

}
