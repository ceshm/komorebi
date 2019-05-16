package engineTester;

/*import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import java.nio.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
*/
import org.lwjgl.Version;
import renderEngine.DisplayManager;

import static org.lwjgl.glfw.GLFW.*;


public class MainGameLoop {

    public static void main(String[] args) {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        long window = DisplayManager.createDisplay();
/*
        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        float[] vertices = {
                -0.5f, 0.5f, 0f,//v0
                -0.5f, -0.5f, 0f,//v1
                0.5f, -0.5f, 0f,//v2
                0.5f, 0.5f, 0f,//v3
        };

        int[] indices = {
                0,1,3,//top left triangle (v0, v1, v3)
                3,1,2//bottom right triangle (v3, v1, v2)
        };
        RawModel model = loader.loadToVertexArray(vertices, indices);*/

        while (!glfwWindowShouldClose(window)) {
            //renderer.prepare();
            //renderer.render(model);
            DisplayManager.updateDisplay();
        }

        //loader.cleanUp();
        DisplayManager.closeDisplay();
    }

}

