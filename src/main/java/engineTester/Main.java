package engineTester;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

import java.awt.*;


public class Main {

    public Main(){
        long window = DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("src/main/resources/sun.png"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        System.out.println(model.getVaoID());
        System.out.println(model.getVertexCount());

        while (!glfwWindowShouldClose(window)) {
            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();

            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }


    public static void main(String[] args) {
        new Main();
    }

}
