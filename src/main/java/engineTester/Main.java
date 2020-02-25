package engineTester;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import entity.Camera;
import entity.Entity;
import entity.Light;
import models.TexturedModel;
import org.joml.Vector3f;
import renderEngine.*;
import models.RawModel;

import shaders.StaticShader;
import textures.ModelTexture;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

        //RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        RawModel model = OBJLoader.loadModel("dragon", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("src/main/resources/stallTexture.png"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        texture.setShineDamper(10);
        texture.setReflectivity(1);


        ArrayList<Entity> entities = new ArrayList<Entity>();
        Random rng = new Random();
        for (int i = 0; i < 20; i++) {
            entities.add(new Entity(
                    texturedModel,
                    new Vector3f(
                            rng.nextFloat() * 50 - 25,
                            rng.nextFloat() * 50 - 25,
                            rng.nextFloat() * -150),
                    rng.nextFloat() * 180f,
                    rng.nextFloat() * 180f,
                    0f,
                    1f
            ));
        }

        Light light = new Light(new Vector3f(0,10,0), new Vector3f(1,1,1));
        Camera camera = new Camera();

        //Entity entity = new Entity(texturedModel, new Vector3f(0,-1.5f,-25), 0,0,0, 1);

        MasterRenderer renderer = new MasterRenderer();

        while (!glfwWindowShouldClose(window)) {
            //entity.increasePosition(0, 0,-0.1f);
            //entity.increaseRotation(0,0.5f,0);
            camera.move();
            entities.forEach(renderer::processEntity);
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();

        loader.cleanUp();
        DisplayManager.closeDisplay();
    }


    public static void main(String[] args) {
        new Main();
    }

}
