package engineTester;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import entity.Camera;
import entity.Entity;
import entity.Light;
import models.TexturedModel;
import org.joml.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

import java.awt.*;

/**
 * THIS IS THE ENTRY POINT for the engine ATM
 *
 * @autor ceshm
 *
 */
public class Main {

    public Main(){
        long window = DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        //RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        RawModel model = OBJLoader.loadModel("dragon", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("src/main/resources/stallTexture.png"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        Entity entity = new Entity(texturedModel, new Vector3f(0,-1.5f,-25), 0,0,0, 1);
        Light light = new Light(new Vector3f(0,0,-20), new Vector3f(1,1,1));
        Camera camera = new Camera();

        //System.out.println(model.getVaoID());
        //System.out.println(model.getVertexCount());

        while (!glfwWindowShouldClose(window)) {
            //entity.increasePosition(0, 0,-0.1f);
            entity.increaseRotation(0,0.5f,0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
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
