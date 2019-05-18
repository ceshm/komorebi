package engineTester;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import entity.Entity;
import models.TexturedModel;
import org.joml.Vector3f;
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
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);


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
        ModelTexture texture = new ModelTexture(loader.loadTexture("src/main/resources/marble2.png"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        Entity entity = new Entity(texturedModel, new Vector3f(0,0,-1), 0,0,0, 1);

        System.out.println(model.getVaoID());
        System.out.println(model.getVertexCount());

        while (!glfwWindowShouldClose(window)) {
            entity.increasePosition(0, 0,-0.1f);
            //entity.increaseRotation(0,1,0);

            renderer.prepare();
            shader.start();
            renderer.render(entity, shader);
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
